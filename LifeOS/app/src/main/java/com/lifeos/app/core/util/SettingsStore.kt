package com.lifeos.app.core.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private val Context.dataStore by preferencesDataStore(name = "lifeos_settings")

/**
 * Central app settings — Section 59 (Settings screen) & Rule #7 ("AI key is
 * user-provided and stored only on-device"). No settings are ever synced
 * off-device except through the explicit Backup/Export flow.
 */
class SettingsStore(private val context: Context) {

    private object Keys {
        val APP_LOCK_ENABLED = booleanPreferencesKey("app_lock_enabled")
        val DARK_THEME_ENABLED = booleanPreferencesKey("dark_theme_enabled")
        val ONBOARDING_COMPLETE = booleanPreferencesKey("onboarding_complete")
        val AI_API_KEY = stringPreferencesKey("ai_api_key")
        val AI_FEATURES_ENABLED = booleanPreferencesKey("ai_features_enabled")
    }

    val appLockEnabled: Flow<Boolean> = context.dataStore.data.map { it[Keys.APP_LOCK_ENABLED] ?: false }
    val darkThemeEnabled: Flow<Boolean> = context.dataStore.data.map { it[Keys.DARK_THEME_ENABLED] ?: false }
    val onboardingComplete: Flow<Boolean> = context.dataStore.data.map { it[Keys.ONBOARDING_COMPLETE] ?: false }
    val aiFeaturesEnabled: Flow<Boolean> = context.dataStore.data.map { it[Keys.AI_FEATURES_ENABLED] ?: false }
    val aiApiKey: Flow<String?> = context.dataStore.data.map { it[Keys.AI_API_KEY] }

    suspend fun setAppLockEnabled(enabled: Boolean) = context.dataStore.edit { it[Keys.APP_LOCK_ENABLED] = enabled }
    suspend fun setDarkThemeEnabled(enabled: Boolean) = context.dataStore.edit { it[Keys.DARK_THEME_ENABLED] = enabled }
    suspend fun setOnboardingComplete(complete: Boolean) = context.dataStore.edit { it[Keys.ONBOARDING_COMPLETE] = complete }
    suspend fun setAiFeaturesEnabled(enabled: Boolean) = context.dataStore.edit { it[Keys.AI_FEATURES_ENABLED] = enabled }
    suspend fun setAiApiKey(key: String) = context.dataStore.edit { it[Keys.AI_API_KEY] = key }
    suspend fun clearAiApiKey() = context.dataStore.edit { it.remove(Keys.AI_API_KEY) }

    /**
     * Synchronous read used only by [com.lifeos.app.core.ai.AiClient]'s key-provider
     * lambda, which is always invoked from a background (IO) coroutine — never
     * from the main thread — so this blocking read is safe.
     */
    fun aiApiKeyBlocking(): String? = runBlocking { aiApiKey.first() }
}
