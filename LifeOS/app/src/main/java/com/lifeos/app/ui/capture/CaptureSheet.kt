package com.lifeos.app.ui.capture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lifeos.app.core.di.LocalServiceLocator
import com.lifeos.app.core.util.DateTimeUtils
import com.lifeos.app.data.db.entities.CaptureType
import kotlinx.coroutines.launch

private enum class CaptureMode { MENU, PHOTO, VIDEO, AUDIO }

/**
 * The "Capture" quick-entry sheet reachable from the Home FAB (Section 3:
 * Life Capture). All four capture types write real CaptureEntity rows:
 *  - THOUGHT: inline text, saved directly
 *  - PHOTO: real CameraX capture (CameraCaptureScreen.kt)
 *  - VIDEO: real CameraX VideoCapture recording (VideoCaptureScreen.kt)
 *  - AUDIO: real MediaRecorder capture (AudioCaptureScreen.kt)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaptureSheet(onDismiss: () -> Unit) {
    val locator = LocalServiceLocator.current
    val scope = rememberCoroutineScope()
    var mode by remember { mutableStateOf(CaptureMode.MENU) }
    var thought by remember { mutableStateOf("") }

    fun saveCapture(type: CaptureType, filePath: String?, caption: String?) {
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
            onDismiss()
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
                    onClick = { saveCapture(CaptureType.THOUGHT, null, thought) },
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
                androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxWidth().height(420.dp)) {
                    CameraCaptureScreen(
                        onCaptured = { path -> saveCapture(CaptureType.PHOTO, path, null) },
                        onCancel = { mode = CaptureMode.MENU }
                    )
                }
            }

            CaptureMode.AUDIO -> Column(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)) {
                AudioCaptureScreen(
                    onCaptured = { path -> saveCapture(CaptureType.AUDIO, path, null) },
                    onCancel = { mode = CaptureMode.MENU }
                )
            }

            CaptureMode.VIDEO -> Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            ) {
                androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxWidth().height(420.dp)) {
                    VideoCaptureScreen(
                        onCaptured = { path -> saveCapture(CaptureType.VIDEO, path, null) },
                        onCancel = { mode = CaptureMode.MENU }
                    )
                }
            }

        }
    }
}
