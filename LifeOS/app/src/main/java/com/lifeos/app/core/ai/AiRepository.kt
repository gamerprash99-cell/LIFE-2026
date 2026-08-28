package com.lifeos.app.core.ai

/**
 * Assembles prompts for every AI-touching feature in LifeOS and is the ONLY
 * place that talks to [AiClient]. Keeping context-assembly here (rather than
 * in ViewModels) means every feature gets the same system prompt discipline:
 * concise, grounded only in what's passed in, never inventing data.
 */
class AiRepository(private val client: AiClient) {

    private val baseSystemPrompt = """
        You are the LifeOS AI Assistant, built into a personal life-management app.
        Be concise, warm, and practical. Never invent facts about the user's data —
        only use what is given to you in the prompt. When asked to extract tasks,
        return them as a clean list, one per line, with no extra commentary.
    """.trimIndent()

    suspend fun runNoteAction(action: NoteAiAction, noteText: String): AiClient.AiResult {
        val prompt = "${action.instruction}\n\n---\n$noteText"
        return client.complete(systemPrompt = baseSystemPrompt, userPrompt = prompt, maxTokens = 700)
    }

    /** Task extraction (Section 8/10) — returns raw lines; caller parses into ExtractedTask. */
    suspend fun extractTasks(sourceText: String): AiClient.AiResult {
        val prompt = """
            Read the following text and extract any actionable tasks or to-dos.
            Return ONLY a plain list, one task per line, no numbering, no bullets,
            no extra commentary. If a task has an implied deadline, append it in
            parentheses, e.g. "Submit report (Friday)". If there are no tasks,
            reply with exactly: NONE

            Text:
            ---
            $sourceText
        """.trimIndent()
        return client.complete(systemPrompt = baseSystemPrompt, userPrompt = prompt, maxTokens = 400)
    }

    /** Diary entry drafting from a rough capture/thought (Rule #8: must be reviewable). */
    suspend fun draftDiaryEntry(rawThoughts: String): AiClient.AiResult {
        val prompt = """
            Turn the following rough notes/thoughts into a short, natural first-person
            diary entry (3-6 sentences), in the same tone as the notes. Do not add
            events or feelings that are not implied by the notes.

            Notes:
            ---
            $rawThoughts
        """.trimIndent()
        return client.complete(systemPrompt = baseSystemPrompt, userPrompt = prompt, maxTokens = 400)
    }

    /** Weekly/monthly review summary (Section 55/56) built from pre-aggregated stats, not raw DB access. */
    suspend fun generateReviewSummary(statsBlock: String, periodLabel: String): AiClient.AiResult {
        val prompt = """
            Here is a summary of the user's $periodLabel activity across LifeOS:

            $statsBlock

            Write a short, encouraging $periodLabel review (4-6 sentences) that
            highlights 1-2 wins and 1 gentle suggestion for next $periodLabel.
            Do not invent numbers not present above.
        """.trimIndent()
        return client.complete(systemPrompt = baseSystemPrompt, userPrompt = prompt, maxTokens = 500)
    }

    /** Free-form AI Assistant chat (Section 55), with recent conversation history. */
    suspend fun chat(history: List<ChatMessage>, contextBlock: String?): AiClient.AiResult {
        val lastUserMessage = history.lastOrNull { it.role == "user" }?.content.orEmpty()
        val historyText = history.dropLast(1).joinToString("\n") { "${it.role}: ${it.content}" }
        val prompt = buildString {
            if (!contextBlock.isNullOrBlank()) {
                append("Relevant data from the user's LifeOS (use only this, don't invent more):\n")
                append(contextBlock)
                append("\n\n")
            }
            if (historyText.isNotBlank()) {
                append("Conversation so far:\n$historyText\n\n")
            }
            append("User: $lastUserMessage")
        }
        return client.complete(systemPrompt = baseSystemPrompt, userPrompt = prompt, maxTokens = 700)
    }

    fun parseExtractedTasks(rawText: String): List<ExtractedTask> {
        if (rawText.trim().equals("NONE", ignoreCase = true)) return emptyList()
        return rawText.lines()
            .map { it.trim().removePrefix("-").removePrefix("*").trim() }
            .filter { it.isNotBlank() }
            .map { line ->
                val match = Regex("""\((.*?)\)\s*$""").find(line)
                val hint = match?.groupValues?.get(1)
                val title = if (match != null) line.removeRange(match.range).trim() else line
                ExtractedTask(title = title, dueDateHint = hint)
            }
    }
}
