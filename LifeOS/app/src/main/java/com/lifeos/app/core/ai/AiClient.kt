package com.lifeos.app.core.ai

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

/**
 * LifeOS AI infrastructure (Phase 5 / Section 8, 55, 56).
 *
 * IMPORTANT — matches Rules #5, #6, #7 in the spec:
 *  - AI calls are made ONLY when the user explicitly triggers an AI action
 *    (tapping "Ask LifeOS AI", opening the AI Assistant, etc.) — never in
 *    the background.
 *  - The context sent to the model is assembled by the CALLER (e.g.
 *    AiRepository) from only the data the user has permitted for that
 *    action — this client has no direct DB access, by design.
 *  - Nothing this returns is written to the database automatically; every
 *    caller must route suggestions through an explicit user-approval step
 *    (see TaskRepository.createFromAiExtraction, DiaryRepository AI-draft flow).
 *
 * This talks to the Anthropic Messages API. Swap [baseUrl]/[model]/the auth
 * header to point at a different provider without touching any other file.
 */
class AiClient(
    private val apiKeyProvider: () -> String?,
    private val model: String = "claude-sonnet-4-6",
    private val baseUrl: String = "https://api.anthropic.com/v1/messages"
) {
    private val http = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    sealed class AiResult {
        data class Success(val text: String) : AiResult()
        data class Error(val message: String) : AiResult()
        object NoApiKey : AiResult()
    }

    @Serializable
    private data class AiMessage(val role: String, val content: String)

    @Serializable
    private data class AiRequestBody(
        val model: String,
        val max_tokens: Int,
        val system: String? = null,
        val messages: List<AiMessage>
    )

    /**
     * Sends a single-turn (or short multi-turn) prompt and returns the model's
     * text reply. All LifeOS AI features (note actions, task extraction,
     * diary organization, weekly/monthly summaries, AI assistant chat) funnel
     * through this one method so there is exactly one place API behavior lives.
     */
    suspend fun complete(
        systemPrompt: String,
        userPrompt: String,
        maxTokens: Int = 1024
    ): AiResult = withContext(Dispatchers.IO) {
        val apiKey = apiKeyProvider()
        if (apiKey.isNullOrBlank()) return@withContext AiResult.NoApiKey

        try {
            val body = AiRequestBody(
                model = model,
                max_tokens = maxTokens,
                system = systemPrompt,
                messages = listOf(AiMessage(role = "user", content = userPrompt))
            )
            val requestJson = json.encodeToString(body)

            val request = Request.Builder()
                .url(baseUrl)
                .addHeader("x-api-key", apiKey)
                .addHeader("anthropic-version", "2023-06-01")
                .addHeader("content-type", "application/json")
                .post(requestJson.toRequestBody("application/json".toMediaType()))
                .build()

            http.newCall(request).execute().use { response ->
                val responseBody = response.body?.string().orEmpty()
                if (!response.isSuccessful) {
                    return@withContext AiResult.Error("AI request failed (${response.code}): $responseBody")
                }
                val text = extractText(responseBody)
                if (text.isNullOrBlank()) {
                    AiResult.Error("AI returned an empty response.")
                } else {
                    AiResult.Success(text)
                }
            }
        } catch (e: Exception) {
            AiResult.Error(e.message ?: "Unknown AI error")
        }
    }

    /** Minimal, dependency-free parse of the Anthropic Messages response shape. */
    private fun extractText(rawJson: String): String? {
        val element = json.parseToJsonElement(rawJson)
        val contentArray = element.jsonObjectOrNull()?.get("content")?.jsonArrayOrNull() ?: return null
        return contentArray.mapNotNull { it.jsonObjectOrNull()?.get("text")?.jsonPrimitiveOrNull()?.content }
            .joinToString("\n")
    }

    private fun kotlinx.serialization.json.JsonElement.jsonObjectOrNull() =
        this as? kotlinx.serialization.json.JsonObject

    private fun kotlinx.serialization.json.JsonElement.jsonArrayOrNull() =
        this as? kotlinx.serialization.json.JsonArray

    private fun kotlinx.serialization.json.JsonElement.jsonPrimitiveOrNull() =
        this as? kotlinx.serialization.json.JsonPrimitive
}
