package com.example.drawwithar.feature.drawingpage.uicomponent

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.example.drawwithar.feature.drawingpage.model.DrawingImageOrientation

@Composable
fun DrawingImage(
    modifier: Modifier = Modifier,
    viewModel: DrawingViewModel,
    src: Uri,
    alpha: Float = 0.5f
) {




    val drawingImageOrientation by viewModel.drawingImageOrientation.collectAsState()
    val flipScaleX by animateFloatAsState(
        targetValue = if (drawingImageOrientation == DrawingImageOrientation.FLIPPED_HORIZONTAL) -1f else 1f,
        label = ""
    )
    val flipScaleY by animateFloatAsState(
        targetValue = if (drawingImageOrientation == DrawingImageOrientation.FLIPPED_VERTICAL) -1f else 1f,
        label = ""
    )

    var scale by rememberSaveable { mutableFloatStateOf(1f) }
    var rotation by rememberSaveable { mutableFloatStateOf(0f) }
    var offsetX by rememberSaveable { mutableFloatStateOf(0f) }
    var offsetY by rememberSaveable { mutableFloatStateOf(0f) }

    val isDrawingImageFrozen by viewModel.isDrawingImageFrozen.collectAsState()

    val dragDamping = 0.2f // Damping factor for smoother dragging

    // Gesture detection
    val gestureModifier = if (!isDrawingImageFrozen) {
        Modifier.pointerInput(Unit) {
            detectTransformGestures { _, pan, zoom, rotate ->
                scale = (scale * zoom).coerceIn(0.5f, 2f) // Limits: 0.5x (zoom out) to 2x (zoom in)
                rotation += rotate

                // Adjust pan based on orientation
                val adjustedPanX = pan.x * (if (flipScaleX == -1f) -1 else 1)
                val adjustedPanY = pan.y * (if (flipScaleY == -1f) -1 else 1)

                // Apply damping to adjusted pan
                offsetX = (offsetX + adjustedPanX * dragDamping).coerceIn(-150f, 150f) // max bounds
                offsetY = (offsetY + adjustedPanY * dragDamping).coerceIn(-250f, 250f)

//            // Apply damping to pan (dragging)
//            offsetX = (offsetX + pan.x * dragDamping).coerceIn(-150f, 150f) // max bounds
//            offsetY = (offsetY + pan.y * dragDamping).coerceIn(-250f, 250f)
            }
        }
    }
    else {
        Modifier
    }


    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .wrapContentSize()
                .scale(scale)
                .offset(
                    x = offsetX.dp,
                    y = offsetY.dp
                )
                .graphicsLayer(
                    scaleX = flipScaleX,
                    scaleY = flipScaleY,
                    rotationZ = rotation,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp,
                    if (isDrawingImageFrozen) Color.Red else Color.White,
                    RoundedCornerShape(8.dp)
                )
                .then(gestureModifier)
            ,
            painter = rememberAsyncImagePainter(src),
            contentDescription = "Captured image",
            alpha = alpha // change alpha for transparency
        )
    }

}