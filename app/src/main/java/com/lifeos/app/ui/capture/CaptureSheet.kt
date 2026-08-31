package com.lifeos.app.ui.capture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lifeos.app.core.di.LocalServiceLocator
import com.lifeos.app.core.util.DateTimeUtils
import com.lifeos.app.data.db.entities.CaptureType
import kotlinx.coroutines.launch

private enum class CaptureMode { MENU, PHOTO, VIDEO, AUDIO, CONFIRM }

/** Holds what was just captured so the CONFIRM state can show a real preview of it. */
private data class JustCaptured(val type: CaptureType, val filePath: String?)

/**
 * The "Capture" quick-entry sheet reachable from the Home FAB (Section 3:
 * Life Capture). All four capture types write real CaptureEntity rows:
 *  - THOUGHT: inline text, saved directly
 *  - PHOTO: real CameraX capture (CameraCaptureScreen.kt)
 *  - VIDEO: real CameraX VideoCapture recording (VideoCaptureScreen.kt)
 *  - AUDIO: real MediaRecorder capture (AudioCaptureScreen.kt)
 *
 * BUG FIX (UI/UX pass): previously, after a successful Photo/Video/Audio
 * capture, this sheet called onDismiss() immediately — the sheet just
 * vanished with no confirmation, even though the file and database row
 * were saved correctly. The data path was never broken; only the missing
 * feedback was. This now shows a CONFIRM state with a real preview of the
 * captured file (see CaptureMediaPreview.kt) before the sheet closes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaptureSheet(onDismiss: () -> Unit) {
    val locator = LocalServiceLocator.current
    val scope = rememberCoroutineScope()
    var mode by remember { mutableStateOf(CaptureMode.MENU) }
    var thought by remember { mutableStateOf("") }
    var justCaptured by remember { mutableStateOf<JustCaptured?>(null) }

    fun saveCapture(type: CaptureType, filePath: String?, caption: String?, showConfirmation: Boolean) {
        scope.launch {
            val now = DateTimeUtils.today()
            val minutes = java.time.LocalTime.now().let { it.hour * 60 + it.minute }
            locator.captureRepository.addCapture(
                type = type,
                filePath = filePath,
                caption = caption,
                dateEpochDay = now.toEpochDay(),
                timeMinutes = minutes
            )
            if (showConfirmation) {
                justCaptured = JustCaptured(type, filePath)
                mode = CaptureMode.CONFIRM
            } else {
                onDismiss()
            }
        }
    }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        when (mode) {
            CaptureMode.MENU -> Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Capture a moment", style = MaterialTheme.typography.titleLarge)

                OutlinedTextField(
                    value = thought,
                    onValueChange = { thought = it },
                    placeholder = { Text("Jot a quick thought…") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = { saveCapture(CaptureType.THOUGHT, null, thought, showConfirmation = false) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = thought.isNotBlank()
                ) { Text("Save thought") }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(onClick = { mode = CaptureMode.PHOTO }, modifier = Modifier.fillMaxWidth().weight(1f)) {
                        Icon(Icons.Filled.Camera, contentDescription = null, modifier = Modifier.padding(end = 4.dp))
                        Text("Photo")
                    }
                    OutlinedButton(onClick = { mode = CaptureMode.VIDEO }, modifier = Modifier.fillMaxWidth().weight(1f)) {
                        Icon(Icons.Filled.Videocam, contentDescription = null, modifier = Modifier.padding(end = 4.dp))
                        Text("Video")
                    }
                    OutlinedButton(onClick = { mode = CaptureMode.AUDIO }, modifier = Modifier.fillMaxWidth().weight(1f)) {
                        Icon(Icons.Filled.Mic, contentDescription = null, modifier = Modifier.padding(end = 4.dp))
                        Text("Audio")
                    }
                }
            }

            CaptureMode.PHOTO -> Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            ) {
                Box(modifier = Modifier.fillMaxWidth().height(420.dp)) {
                    CameraCaptureScreen(
                        onCaptured = { path -> saveCapture(CaptureType.PHOTO, path, null, showConfirmation = true) },
                        onCancel = { mode = CaptureMode.MENU }
                    )
                }
            }

            CaptureMode.AUDIO -> Column(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)) {
                AudioCaptureScreen(
                    onCaptured = { path -> saveCapture(CaptureType.AUDIO, path, null, showConfirmation = true) },
                    onCancel = { mode = CaptureMode.MENU }
                )
            }

            CaptureMode.VIDEO -> Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth().height(420.dp)) {
                    VideoCaptureScreen(
                        onCaptured = { path -> saveCapture(CaptureType.VIDEO, path, null, showConfirmation = true) },
                        onCancel = { mode = CaptureMode.MENU }
                    )
                }
            }

            CaptureMode.CONFIRM -> {
                val captured = justCaptured
                val captureType = captured?.type
                val capturePath = captured?.filePath
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp).padding(end = 8.dp)
                        )
                        Text(
                            when (captureType) {
                                CaptureType.PHOTO -> "Photo saved"
                                CaptureType.VIDEO -> "Video saved"
                                CaptureType.AUDIO -> "Recording saved"
                                else -> "Saved"
                            },
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    if (capturePath != null) {
                        when (captureType) {
                            CaptureType.PHOTO -> PhotoPreview(capturePath, modifier = Modifier.fillMaxWidth())
                            CaptureType.VIDEO -> VideoPreview(capturePath, modifier = Modifier.fillMaxWidth())
                            CaptureType.AUDIO -> AudioPreview(capturePath, modifier = Modifier.fillMaxWidth())
                            else -> Unit
                        }
                    }

                    Text(
                        "This is now saved in your Timeline. Tap it there anytime to view, play, or delete it.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
                        Text("Done")
                    }
                }
            }
        }
    }
}
