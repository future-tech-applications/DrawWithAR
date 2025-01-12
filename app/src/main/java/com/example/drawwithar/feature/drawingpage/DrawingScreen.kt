package com.example.drawwithar.feature.drawingpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.core.camera.OpenCamera
import com.example.drawwithar.core.gallery.OpenGallery
import com.example.drawwithar.core.common.ui.components.BorderedButton
import com.example.drawwithar.core.common.ui.components.CustomTopAppBar
import com.example.drawwithar.core.common.sharedviewmodel.SharedViewModel
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.feature.homepage.navigation.HomePageRoutes
import com.example.drawwithar.util.navigateTo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun DrawingScreen(
    navController: NavHostController,
    viewModel: DrawingViewModel,
) {
    // get shared viewmodel
    val sharedViewModel = getSharedViewModel()

    // to get image from gallery
    val showGallery by sharedViewModel.showGallery.collectAsState()

    // to store image for drawing in this variable
    val imageUri by sharedViewModel.imageUri.collectAsState()

    // to check if drawing started or not (if image uri is not empty, drawing started)
    val isStartDrawing by sharedViewModel.isStartDrawing.collectAsState()

    // to store alpha value when user slides to adjust opacity
    val alphaValue by viewModel.alphaValue.collectAsState()

    // to check if opacity slider is visible or not
    val isOpacitySliderVisible by viewModel.isOpacitySliderVisible.collectAsState()

    fun finishDrawing() {
        sharedViewModel.toggleDrawing()
        viewModel.resetDrawingImageStates()
    }


    Scaffold(
        topBar = {
            CustomTopAppBar(
                title =  "Draw with AR",
                onBackPressed = {
                    finishDrawing()
                    navController.navigateTo(
                        dest = HomePageRoutes.HomePage.route,
                        removeCurrentPageOnPop = true,
                        restorePageState = false,
                    )
                },
                isShowBackBtn = true,
                actions = {
                    // => Button to Start or Finish Drawing
                    if(imageUri!= EMPTY_IMAGE_URI) {
                        BorderedButton(
                            text = if(isStartDrawing) "Finish" else "Start",
                            onClick = {
                                finishDrawing()
                            }
                        )
                    }
                }
            )
                 },
        )
    { innerPadding ->
        // if image uri is not empty, show drawing started content
        if (imageUri != EMPTY_IMAGE_URI) {
            DrawingStartedContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = MaterialTheme.colorScheme.surfaceContainer),
                viewModel = viewModel,
                imageUri = imageUri,
                alphaValue = alphaValue,
                isOpacitySliderVisible = isOpacitySliderVisible,
                isStartDrawing = isStartDrawing
            )
        }
        // if image uri is empty, show camera preview
        else {
            // case1: Open Gallery explicitly to have an image
            if (showGallery) {
                OpenGallery(

                    modifier = Modifier,
                    onImageUri = {
                        sharedViewModel.selectImage(it)
                        sharedViewModel.toggleShowGallery()
                    }
                )
            } else {
                // case2: Open Camera by default to capture an image
                OpenCamera(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                )
            }
        }
    }



}

