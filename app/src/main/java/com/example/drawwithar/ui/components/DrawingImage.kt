package com.example.drawwithar.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
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

@Composable
fun DrawingImage(
    modifier: Modifier = Modifier,
    src: Uri,
    alpha: Float = 0.5f
) {

    // image size adjustments
    var scale by rememberSaveable { mutableFloatStateOf(1f) }
    var rotation by rememberSaveable { mutableFloatStateOf(0f) }
    var offsetX by rememberSaveable { mutableFloatStateOf(0f) }
    var offsetY by rememberSaveable { mutableFloatStateOf(0f) }

    val dragDamping = 0.2f // Damping factor for smoother dragging

    // Gesture detection
    val gestureModifier = Modifier.pointerInput(Unit) {
        detectTransformGestures { _, pan, zoom, rotate ->
            scale = (scale * zoom).coerceIn(0.5f, 2f) // Limits: 0.5x (zoom out) to 2x (zoom in)
            rotation += rotate

            // Apply damping to pan (dragging)
            offsetX = (offsetX + pan.x * dragDamping).coerceIn(-150f, 150f) // max bounds
            offsetY = (offsetY + pan.y * dragDamping).coerceIn(-250f, 250f)
        }
    }


    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .scale(scale)
                .offset(
                    x = offsetX.dp,
                    y = offsetY.dp
                )
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                .then(gestureModifier)
            ,
            painter = rememberAsyncImagePainter(src),
            contentDescription = "Captured image",
            alpha = alpha // change alpha for transparency
        )
    }

}