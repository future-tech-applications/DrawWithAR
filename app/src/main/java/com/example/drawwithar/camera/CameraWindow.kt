//package com.example.drawwithar.camera
//
//import android.content.Context
//import android.util.Log
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
//import androidx.camera.core.Preview
//import androidx.camera.core.UseCase
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.launch
//
//@Composable
//fun CameraWindow(modifier: Modifier, context: Context) {
//    Box(modifier = modifier) {
//        val lifecycleOwner = LocalLifecycleOwner.current
//        val coroutineScope = rememberCoroutineScope()
//        var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
//        val imageCaptureUseCase by remember {
//            mutableStateOf(
//                ImageCapture.Builder()
//                    .setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY)
//                    .build()
//            )
//        }
//        Box {
//            CameraPreview(
//                modifier = Modifier.fillMaxSize(),
//                onUseCase = {
//                    previewUseCase = it
//                }
//            )
//            CapturePictureButton(
//                modifier = Modifier
//                    .size(100.dp)
//                    .padding(16.dp)
//                    .align(Alignment.BottomCenter),
//                onClick = {
//                    coroutineScope.launch {
//                        onImageFile(imageCaptureUseCase.takePicture(context.executor))
//                    }
//                }
//            )
//        }
//        LaunchedEffect(previewUseCase) {
//            val cameraProvider = context.getCameraProvider()
//            try {
//                // Must unbind the use-cases before rebinding them.
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
//                )
//            } catch (ex: Exception) {
//                Log.e("CameraCapture", "Failed to bind camera use cases", ex)
//            }
//        }
//    }
//}
