package com.example.drawwithar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.example.drawwithar.camera.CameraCapture
import com.example.drawwithar.gallery.GallerySelect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var imageUri by remember { mutableStateOf(EMPTY_IMAGE_URI) }
    var isStartDrawing by remember { mutableStateOf(false) }

    if (imageUri != EMPTY_IMAGE_URI) {
        Box(modifier = modifier) {
            // when AR enabled to start drawing //

            // 1 => Camera Preview to open live camera
            if(isStartDrawing) CameraCapture(isDrawing = isStartDrawing)

            // 2 => Open and Overlay Image to Draw
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = "Captured image",
                alpha = 0.2f // change alpha for transparency
            )

            // Button to Enable AR and start Drawing button
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    imageUri = if(isStartDrawing) EMPTY_IMAGE_URI else imageUri
                    isStartDrawing = !isStartDrawing
                }
            ) {
                val text = if(isStartDrawing ) "Reset" else "Start Drawing"
                Text(text)
            }
        }
    }

    // to get image from gallery //
    else {
        var showGallerySelect by remember { mutableStateOf(false) }
        if (showGallerySelect) {
            GallerySelect(
                modifier = modifier,
                onImageUri = { uri ->
                    showGallerySelect = false
                    imageUri = uri
                }
            )
        } else {

            // Camera Preview
            Box(modifier = modifier) {
                CameraCapture(
                    modifier = modifier,
                    onImageFile = { file ->
                        imageUri = file.toUri()
                    }
                )
                Button(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(4.dp),
                    onClick = {
                        showGallerySelect = true
                    }
                ) {
                    Text("Select from Gallery")
                }
            }
        }
    }
}