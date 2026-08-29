package com.lifeos.app.core.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

/**
 * Runtime permission helpers used only by the specific screen that needs
 * them (Capture → Camera/Audio), never requested at app launch — Rule #22
 * of the spec: "Request Android permissions only when required."
 */
object PermissionManager {
    fun hasPermission(context: Context, permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}

/** Remembers a single-permission launcher and current grant state, re-checked on each recomposition trigger. */
@Composable
fun rememberPermissionState(permission: String): PermissionState {
    val context = LocalContext.current
    var granted by remember { mutableStateOf(PermissionManager.hasPermission(context, permission)) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        granted = isGranted
    }

    return remember(granted) {
        PermissionState(
            isGranted = granted,
            request = { launcher.launch(permission) }
        )
    }
}

data class PermissionState(
    val isGranted: Boolean,
    val request: () -> Unit
)

object LifeOSPermissions {
    const val CAMERA = Manifest.permission.CAMERA
    const val RECORD_AUDIO = Manifest.permission.RECORD_AUDIO
}
