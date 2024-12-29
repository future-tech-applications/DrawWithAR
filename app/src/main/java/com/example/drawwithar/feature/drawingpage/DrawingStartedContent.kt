package com.example.drawwithar.feature.drawingpage

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drawwithar.core.camera.CameraCapture
import com.example.drawwithar.feature.drawingpage.model.OpacitySliderModel
import com.example.drawwithar.feature.drawingpage.uicomponent.DrawingControlBottomBar
import com.example.drawwithar.feature.drawingpage.uicomponent.DrawingImage
import com.example.drawwithar.feature.drawingpage.uicomponent.getListOfBottomControlItems
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun DrawingStartedContent(
    modifier: Modifier = Modifier,
    viewModel: DrawingViewModel,
    isStartDrawing: Boolean,
    imageUri: Uri,
    alphaValue: Float,
    isOpacitySliderVisible: Boolean
) {
    Box(modifier = modifier) {
        // 1 => Camera Preview to open running camera
        if(isStartDrawing) CameraCapture(modifier = Modifier.fillMaxSize(), isDrawing = isStartDrawing)

        // 2 => Open and Overlay the Image on top of Camera Preview to draw the image
        DrawingImage(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp),
            viewModel = viewModel,
            src = imageUri,
            initialAlpha = alphaValue,
        )

        // 3 => Bottom Controls
        val controlItems by viewModel.drawingControlItems.collectAsState()

        // this data helps to manage OpacitySlider state
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

        // show drawing control bottom bar when drawing is started
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