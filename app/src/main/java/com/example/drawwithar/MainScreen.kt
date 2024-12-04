package com.example.drawwithar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
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
    var isStartDrawing by remember { mutableStateOf(true) }
    var alphaValue by remember { mutableFloatStateOf(0.5f) }

    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    val density = LocalDensity.current
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp





    if (imageUri != EMPTY_IMAGE_URI) {
        Box(modifier = modifier) {
            // when AR enabled to start drawing //

            // 1 => Camera Preview to open live camera
            if(isStartDrawing) CameraCapture(isDrawing = isStartDrawing)

            // 2 => Open and Overlay Image to Draw
            Column(
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .fillMaxSize()
                    .padding(16.dp)
                ,
            ) {
                Image(
                    modifier = Modifier
                        .scale(scale)
                        .offset(
                            x = offsetX.dp,
                            y = offsetY.dp
                        )
                        .border(1.dp, Color.White)
                        .pointerInput(Unit) {
                            detectTransformGestures { _, pan, zoom, _ ->
                                offsetX += pan.x
                                offsetY += pan.y
                                scale *= zoom.coerceIn(1f, 5f)
                            }
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                    ,
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Captured image",
                    alpha = alphaValue // change alpha for transparency
                )
            }

            // Controls
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
                ,
            ) {
                Slider(
                    value = alphaValue,
                    onValueChange = { alphaValue = it },
                    valueRange = 0f..1f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                // Button to Enable AR and start Drawing button
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                    ,
                    onClick = {
                        imageUri = if(isStartDrawing) EMPTY_IMAGE_URI else imageUri
                        isStartDrawing = !isStartDrawing
                    }
                ) {
                    val text = if(isStartDrawing ) "Finish" else "Start Drawing"
                    Text(text)
                }
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


//.offset(
//x = offsetX.coerceIn(-density.run { screenWidthDp.toPx() } * (scale - 1) / 2, density.run { screenWidthDp.toPx() } * (scale - 1) / 2).dp,
//y = offsetY.coerceIn(-density.run { screenHeightDp.toPx() } * (scale - 1) / 2, density.run { screenHeightDp.toPx() } * (scale - 1) / 2).dp
//)