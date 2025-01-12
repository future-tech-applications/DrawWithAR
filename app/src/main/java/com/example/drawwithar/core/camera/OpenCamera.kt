package com.example.drawwithar.core.camera

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.drawwithar.core.common.sharedviewmodel.SharedViewModel
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun OpenCamera(
    modifier: Modifier = Modifier,
    viewModel: DrawingViewModel
) {
    val sharedViewModel = getSharedViewModel()

    Box(modifier = modifier) {
        // Open Camera
        CameraCapture(
            onImageFile = { file ->
                sharedViewModel.selectImage(file.toUri())
            }
        )
        Button(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(4.dp)
            ,
            onClick = {
                sharedViewModel.toggleShowGallery()
            }
        ) {
            Text("Select from Gallery")
        }
    }
}