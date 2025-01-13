package com.example.drawwithar.feature.savedrawingpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.core.camera.CameraCapture
import com.example.drawwithar.core.common.ui.components.CustomTopAppBar
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.feature.savedrawingpage.navigation.SaveDrawingPageRoutes
import com.example.drawwithar.util.navigateTo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun SaveDrawingCameraScreen(
    navController: NavHostController
) {
    // get shared viewmodel
    val sharedViewModel = getSharedViewModel()

    // save drawing viewmodel
    val saveDrawingViewModel = hiltViewModel<SaveDrawingViewModel>()

    // to get image from gallery
    val showGallery by sharedViewModel.showGallery.collectAsState()

    // to store image for drawing in this variable
    val imageUri by sharedViewModel.imageUri.collectAsState()

    // to check if drawing being reviewed to save
    val isDrawingBeingReviewed by saveDrawingViewModel.isDrawingBeingReviewed.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = if (isDrawingBeingReviewed) "Preview" else "Capture to Save",
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    )
    { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // show saving review
            if(isDrawingBeingReviewed) {
                SaveDrawingReviewScreen(
                    navController = navController,
                    viewModel = saveDrawingViewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(color = MaterialTheme.colorScheme.surfaceContainer),
                    onRetakeClick = {saveDrawingViewModel.toggleIsDrawingBeingReviewed()}
                )
            }
            else {
                // Open Camera
                CameraCapture(
                    onImageFile = { file ->
                        saveDrawingViewModel.toggleIsDrawingBeingReviewed()
                        saveDrawingViewModel.updatePreviewImageUri(file.toUri())
                    }
                )
            }
        }
    }
}

