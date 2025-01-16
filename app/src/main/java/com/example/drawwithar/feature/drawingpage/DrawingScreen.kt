package com.example.drawwithar.feature.drawingpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.core.camera.OpenCamera
import com.example.drawwithar.core.gallery.OpenGallery
import com.example.drawwithar.core.common.ui.components.BorderedButton
import com.example.drawwithar.core.common.ui.components.CustomTopAppBar
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.core.common.ui.components.ConfirmationDialog
import com.example.drawwithar.core.common.ui.components.TakePhotoDialog
import com.example.drawwithar.feature.homepage.navigation.HomePageRoutes
import com.example.drawwithar.feature.savedrawingpage.navigation.SaveDrawingPageRoutes
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
    val imageUri by sharedViewModel.selectedImageSrcForDrawing.collectAsState()

    // to check if drawing started or not (if image uri is not empty, drawing started)
    val isStartDrawing by sharedViewModel.isStartDrawing.collectAsState()

    // to store alpha value when user slides to adjust opacity
    val alphaValue by viewModel.alphaValue.collectAsState()

    // to check if opacity slider is visible or not
    val isOpacitySliderVisible by viewModel.isOpacitySliderVisible.collectAsState()

    // to check if back button pressed
    val isExitConfirmDialogOpened by viewModel.isExitConfirmDialogOpened.collectAsState()

    // to check if reset drawing option pressed
    val isDrawingStateResetDialogOpened by viewModel.isDrawingStateResetDialogOpened.collectAsState()

    // on finish drawing dialog state
    var isTakeAPhotoDialogOpened = rememberSaveable { mutableStateOf(false) }

    fun finishDrawing() {
        sharedViewModel.toggleDrawing()
        viewModel.resetDrawingImageStates()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title =  "Draw with AR",
                onBackPressed = {
                    viewModel.updateExitConfirmDialogOpened(true)
                },
                isShowBackBtn = true,
                actions = {
                    // => Button to Start or Finish Drawing
                    if(imageUri!= EMPTY_IMAGE_URI) {
                        BorderedButton(
                            text = "Finish",
                            onClick = {
                                isTakeAPhotoDialogOpened.value = true
//                                navController.navigate(
//                                    SaveDrawingPageRoutes.SaveDrawingCameraPage.route,
//                                    navOptions = NavOptions.Builder().setRestoreState(true).build(),
//                                )
                            }
                        )
                    }
                }
            )
                 },
        )
    { innerPadding ->





        // show confirmation dialog on back press
        if(isExitConfirmDialogOpened) {
            ConfirmationDialog(
                modifier = Modifier.fillMaxWidth(),
                title = "Confirm Exit",
                text = "Are you sure you want to exit?",
                onConfirm = {
                    navController.navigate(
                        HomePageRoutes.HomePage.route
                    )
                    finishDrawing()
                    viewModel.updateExitConfirmDialogOpened(false)
                },
                onCancel = {
                    viewModel.updateExitConfirmDialogOpened(false)
                }
            )
        }

        // show confirmation dialog drawing item state reset
        if(isDrawingStateResetDialogOpened) {
            ConfirmationDialog(
                modifier = Modifier.fillMaxWidth(),
                title = "Reset Changes",
                text = "Any changes applied on drawing image will be reset. Are you sure resetting?",
                onConfirm = {
                    viewModel.resetDrawingImageStates()
                    viewModel.updateResetDrawingConfirmDialogOpened(false)
                },
                onCancel = {
                    viewModel.updateResetDrawingConfirmDialogOpened(false)
                }
            )
        }
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
                isStartDrawing = isStartDrawing,
            )

            // on finish drawing dialog
            if(isTakeAPhotoDialogOpened.value) {
                TakePhotoDialog(
                    showDialog = isTakeAPhotoDialogOpened.value,
                    onDismiss = {  },
                    onTakePhotoClick = {  },
                    title = "Take a photo of drawing"
                )
            }
        }
        // if image uri is empty, show camera preview
        else {
            // case1: Open Gallery explicitly to have an image
            if (showGallery) {
                OpenGallery(
                    modifier = Modifier,
                    onImageUri = {
                        sharedViewModel.selectImageForDrawing(it)
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

