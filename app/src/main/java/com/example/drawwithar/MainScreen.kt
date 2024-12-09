package com.example.drawwithar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.camera.CameraCapture
import com.example.drawwithar.gallery.GallerySelect
import com.example.drawwithar.ui.components.BorderedButton
import com.example.drawwithar.ui.components.BottomBarItem
import com.example.drawwithar.ui.components.CustomBottomBar
import com.example.drawwithar.ui.components.CustomTopAppBar
import com.example.drawwithar.ui.components.DrawingControls
import com.example.drawwithar.ui.components.DrawingImage
import com.example.drawwithar.ui.components.getListOfControlItems
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val view = LocalView.current

    var imageUri by rememberSaveable { mutableStateOf(EMPTY_IMAGE_URI) }
    var isStartDrawing by rememberSaveable { mutableStateOf(false) }
    var alphaValue by rememberSaveable { mutableFloatStateOf(0.5f) }

    var selectedTab by remember { mutableStateOf(0) }



    // Hide system bars
    LaunchedEffect(Unit) {
        val windowInsetsController = ViewCompat.getWindowInsetsController(view)
        windowInsetsController?.let {
            it.hide(WindowInsetsCompat.Type.systemBars())
            it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    // Restore system bars on dispose
    DisposableEffect(Unit) {
        onDispose {
            val windowInsetsController = ViewCompat.getWindowInsetsController(view)
            windowInsetsController?.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Draw with AR",
                onBackPresses = {  },
                isShowBackBtn = true,
                actions = {
                    // => Button to Finish Drawing
                    if(isStartDrawing) {
                        BorderedButton(
                            modifier = Modifier.padding(8.dp),
                            text = "Finish",
                            onClick = {
                                imageUri = if(isStartDrawing) EMPTY_IMAGE_URI else imageUri
                                isStartDrawing = !isStartDrawing
                            }
                        )
                    }
                }
            )
        },
        // Main content of the screen
        content = { innerPadding ->
            if (imageUri != EMPTY_IMAGE_URI) {
                Box(modifier = modifier
                    .padding(innerPadding)
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    // 1 => Camera Preview to open live camera
                    if(isStartDrawing) CameraCapture(isDrawing = isStartDrawing)

                    // 2 => Open and Overlay Image to Draw
                    DrawingImage(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxSize()
                            .padding(16.dp),
                        src = imageUri,
                        alpha = alphaValue
                    )

                    // 3 => Bottom Controls
                    val controlItems = getListOfControlItems()
                    CustomBottomBar(
                        items = controlItems,
                        onItemSelected = { selectedTab = it },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )


                    /**
                    DrawingControls(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.BottomCenter)
                        ,
                        alpha = alphaValue,
                        isStartDrawing = isStartDrawing,
                        onStartDrawing = {
                            imageUri = if(isStartDrawing) EMPTY_IMAGE_URI else imageUri
                            isStartDrawing = !isStartDrawing
                        },
                        onAlphaChange = {
                            alphaValue = it
                        }
                    )
                    **/
                }
            }

            // to get image from gallery //
            else {
                var showGallerySelect by rememberSaveable { mutableStateOf(false) }
                // Open Gallery
                if (showGallerySelect) {
                    GallerySelect(
                        modifier = Modifier,
                        onImageUri = { uri ->
                            showGallerySelect = false
                            imageUri = uri
                        }
                    )
                } else {
                    Box(modifier = modifier.padding(innerPadding)) {
                        // Open Camera
                        CameraCapture(
                            modifier = modifier,
                            onImageFile = { file ->
                                imageUri = file.toUri()
                            }
                        )
                        Button(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(4.dp)
                            ,
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
    )

}

