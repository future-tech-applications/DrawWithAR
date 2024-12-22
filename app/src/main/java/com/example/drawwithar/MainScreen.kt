package com.example.drawwithar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.camera.CameraCapture
import com.example.drawwithar.gallery.GallerySelect
import com.example.drawwithar.model.OpacitySliderModel
import com.example.drawwithar.ui.components.BorderedButton
import com.example.drawwithar.ui.components.DrawingControlBottomBar
import com.example.drawwithar.ui.components.CustomTopAppBar
import com.example.drawwithar.ui.components.DrawingImage
import com.example.drawwithar.ui.components.getListOfControlItems
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun MainScreen(viewModel: MainViewModel) {

//    var showGallerySelect by rememberSaveable { mutableStateOf(false) }
//    var imageUri by rememberSaveable { mutableStateOf(EMPTY_IMAGE_URI) }
//    var isStartDrawing by rememberSaveable { mutableStateOf(imageUri != EMPTY_IMAGE_URI) }
//    var alphaValue by rememberSaveable { mutableFloatStateOf(0.5f) }
//
//    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
//    var isOpacitySliderVisible by rememberSaveable { mutableStateOf(true) }

    val showGallery by viewModel.showGallery.collectAsState()
    val imageUri by viewModel.imageUri.collectAsState()
    val isStartDrawing by viewModel.isStartDrawing.collectAsState()
    val alphaValue by viewModel.alphaValue.collectAsState()
    val isOpacitySliderVisible by viewModel.isOpacitySliderVisible.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title =  "Draw with AR",
                onBackPressed = {  },
                isShowBackBtn = true,
                actions = {
                    // => Button to Finish Drawing
                    if(imageUri != EMPTY_IMAGE_URI) {
                        BorderedButton(
                            text = if(isStartDrawing) "Finish" else "Start",
                            onClick = {
                                viewModel.toggleDrawing()
                            }
                        )
                    }
                }
            )
        },
        // Main content of the screen
        content = { innerPadding ->
            if (imageUri != EMPTY_IMAGE_URI) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                ) {

                    // 1 => Camera Preview to open running camera
                    if(isStartDrawing) CameraCapture(modifier = Modifier.fillMaxSize(), isDrawing = isStartDrawing)

                    // 2 => Open and Overlay Image to Draw
                    DrawingImage(
                        modifier = Modifier
                            .padding(16.dp),
                        src = imageUri,
                        alpha = alphaValue
                    )

                    // 3 => Bottom Controls
                    val controlItems = getListOfControlItems()
                    val opacitySliderModel = OpacitySliderModel(
                        alpha = alphaValue,
                        isStartDrawing = isStartDrawing,
                        onStartDrawing = {
                            viewModel.toggleDrawing()
                        },
                        onAlphaChange = {
                            viewModel.updateAlphaValue(it)
                        }
                    )

                    if(isStartDrawing) {
                        DrawingControlBottomBar(
                            viewModel = viewModel,
                            modifier = Modifier.align(Alignment.BottomCenter),
                            isOpacitySliderVisible = isOpacitySliderVisible,
                            opacitySliderModel = opacitySliderModel,
                            items = controlItems
                        )
                    }
                }
            }

            // to get image from gallery //
            else {
                // Open Gallery
                if (showGallery) {
                    GallerySelect(
                        modifier = Modifier,
                        onImageUri = {
                            viewModel.selectImage(it)
                            viewModel.toggleShowGallery()
                        }
                    )
                } else {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                    ) {
                        // Open Camera
                        CameraCapture(
                            onImageFile = { file ->
                                viewModel.selectImage(file.toUri())
                            }
                        )
                        Button(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(4.dp)
                            ,
                            onClick = {
                                viewModel.toggleShowGallery()
                            }
                        ) {
                            Text("Select from Gallery")
                        }
                    }
                }
            }
        }
    )

}

