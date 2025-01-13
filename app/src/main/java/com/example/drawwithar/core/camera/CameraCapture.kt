package com.example.drawwithar.core.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.example.drawwithar.core.eventbus.AppEvent
import com.example.drawwithar.core.eventbus.getAppEventBus
import com.example.drawwithar.util.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import java.io.File
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@SuppressLint("RestrictedApi")
@ExperimentalPermissionsApi
@ExperimentalCoroutinesApi
@Composable
fun CameraCapture(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    onImageFile: (File) -> Unit = { },
    isDrawing: Boolean = false
) {
    val appEventBus = getAppEventBus()
    val context = LocalContext.current
    val hasFlashMode by remember { mutableStateOf(true) } // State for flash mode toggle
    var isFlashLightToggled by remember { mutableStateOf(false) } // State for flash light toggle

    // Ask for Camera permissions
    Permission(
        permission = Manifest.permission.CAMERA,
        rationale = "You said you wanted a picture for drawing, so I'm going to have to ask for permission.",
        permissionNotAvailableContent = {
            Column(modifier) {
                Text("O noes! No Camera! Artist requires its tools!")
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                        )
                    }
                ) {
                    Text("Open Settings")
                }
            }
        }
    ) {
        Box(modifier = modifier) {
            val lifecycleOwner = LocalLifecycleOwner.current
            val coroutineScope = rememberCoroutineScope()
            var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }

            // toggle flash light
            if (isFlashLightToggled) {
                previewUseCase.camera?.cameraControl?.enableTorch(true)
            } else {
                previewUseCase.camera?.cameraControl?.enableTorch(false)
            }

            val imageCaptureUseCase by remember {
                mutableStateOf(
                    ImageCapture.Builder()
                        .setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY)
                        .setFlashMode(
                            if (hasFlashMode) ImageCapture.FLASH_MODE_ON else ImageCapture.FLASH_MODE_OFF
                        )
                        .build()
                )
            }

            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                onUseCase = {
                    previewUseCase = it
                }
            )

            // Show capture button only if camera is not drawing
            if (!isDrawing) {
                CapturePictureButton(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                    onClick = {
                        coroutineScope.launch {
                            onImageFile(imageCaptureUseCase.takePicture(context.executor))
                        }
                    }
                )
            }

            // listen to flash light toggle event
            LaunchedEffect(appEventBus) {
                appEventBus.subscribe(this) {
                        event ->
                    if(event is AppEvent.FlashLightToggledEvent)
                        isFlashLightToggled = event.toggledValue
                }
            }
            LaunchedEffect(previewUseCase) {
                val cameraProvider = context.getCameraProvider()
                try {
                    // Must unbind the use-cases before rebinding them.
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
                    )
                } catch (ex: Exception) {
                    Log.e("CameraCapture", "Failed to bind camera use cases", ex)
                }
            }
        }
    }
}