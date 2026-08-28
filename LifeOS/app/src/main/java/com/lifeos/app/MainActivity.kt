package com.lifeos.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lifeos.app.core.di.LocalServiceLocator
import com.lifeos.app.ui.navigation.LifeOSNavHost
import com.lifeos.app.ui.onboarding.OnboardingScreen
import com.lifeos.app.ui.theme.LifeOSTheme
import kotlinx.coroutines.launch

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val serviceLocator = (application as LifeOSApplication).serviceLocator

        setContent {
            val darkTheme by serviceLocator.settingsStore.darkThemeEnabled.collectAsState(initial = false)

            LifeOSTheme(darkTheme = darkTheme) {
                CompositionLocalProvider(LocalServiceLocator provides serviceLocator) {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        OnboardingGate { AppLockGate { LifeOSNavHost() } }
                    }
                }
            }
        }
    }
}

/** Shows the one-time onboarding flow before anything else, gated by SettingsStore.onboardingComplete. */
@Composable
private fun OnboardingGate(content: @Composable () -> Unit) {
    val locator = LocalServiceLocator.current
    val scope = rememberCoroutineScopeCompat()
    val onboardingComplete by locator.settingsStore.onboardingComplete.collectAsState(initial = false)

    if (onboardingComplete) {
        content()
    } else {
        OnboardingScreen(onFinish = { scope.launch { locator.settingsStore.setOnboardingComplete(true) } })
    }
}

@Composable
private fun rememberCoroutineScopeCompat() = androidx.compose.runtime.rememberCoroutineScope()

/**
 * Gates the whole app behind biometric auth when App Lock is enabled in
 * Settings (Section 2 / Phase 6 hardening). If disabled, renders [content]
 * immediately.
 */
@Composable
private fun AppLockGate(content: @Composable () -> Unit) {
    val locator = LocalServiceLocator.current
    val activity = androidx.compose.ui.platform.LocalContext.current as? FragmentActivity

    val appLockEnabled by locator.settingsStore.appLockEnabled.collectAsState(initial = false)
    var unlocked by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(appLockEnabled, activity) {
        if (appLockEnabled && !unlocked && activity != null) {
            locator.appLockManager.authenticate(
                activity = activity,
                onSuccess = { unlocked = true },
                onError = { message -> errorMessage = message }
            )
        }
    }

    when {
        !appLockEnabled -> content()
        unlocked -> content()
        else -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("LifeOS is locked", style = MaterialTheme.typography.titleLarge)
                errorMessage?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
                Button(onClick = {
                    activity?.let {
                        locator.appLockManager.authenticate(
                            activity = it,
                            onSuccess = { unlocked = true },
                            onError = { message -> errorMessage = message }
                        )
                    }
                }) { Text("Unlock") }
            }
        }
    }
}
