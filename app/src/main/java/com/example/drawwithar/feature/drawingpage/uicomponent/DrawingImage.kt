package com.example.drawwithar.feature.drawingpage.uicomponent

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.drawwithar.core.common.Const
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.example.drawwithar.feature.drawingpage.model.DrawingImageOrientation
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun DrawingImage(
    modifier: Modifier = Modifier,
    viewModel: DrawingViewModel,
    imageSrc: Any,
    initialAlpha: Float = Const.OpacitySlider.INITIAL_VALUE
) {
    val drawingImageOrientation by viewModel.drawingImageOrientation.collectAsState()

    val flipScaleX by animateFloatAsState(
        targetValue = when (drawingImageOrientation) {
            DrawingImageOrientation.FLIPPED_HORIZONTAL, DrawingImageOrientation.FLIPPED_BOTH -> Const.Flip.HORIZONTAL_SCALE
            else -> Const.Flip.DEFAULT_SCALE
        },
        label = ""
    )

    val flipScaleY by animateFloatAsState(
        targetValue = when (drawingImageOrientation) {
            DrawingImageOrientation.FLIPPED_VERTICAL, DrawingImageOrientation.FLIPPED_BOTH -> Const.Flip.VERTICAL_SCALE
            else -> Const.Flip.DEFAULT_SCALE
        },
        label = ""
    )

    val scale by viewModel.scale.collectAsState()
    val rotation by viewModel.rotation.collectAsState()
    val offsetX by viewModel.offsetX.collectAsState()
    val offsetY by viewModel.offsetY.collectAsState()
    val isDrawingImageFrozen by viewModel.isDrawingImageFrozen.collectAsState()

    val gestureModifier = if (!isDrawingImageFrozen) {
        Modifier.pointerInput(Unit) {
            detectTransformGestures { _, pan, zoom, rotate ->
                viewModel.updateScale(scale * zoom)

                val adjustedRotate = rotate * flipScaleX * flipScaleY
                viewModel.updateRotation(rotation + adjustedRotate)

                val rotationRadians = Math.toRadians(rotation.toDouble()).toFloat()
                val adjustedPanX = pan.x * flipScaleX * cos(rotationRadians) - pan.y * flipScaleY * sin(rotationRadians)
                val adjustedPanY = pan.x * flipScaleX * sin(rotationRadians) + pan.y * flipScaleY * cos(rotationRadians)
                viewModel.updateOffset(offsetX + adjustedPanX * Const.Drag.DAMPING_FACTOR, offsetY + adjustedPanY * Const.Drag.DAMPING_FACTOR)
            }
        }
    } else {
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
            painter = if(imageSrc is Uri) rememberAsyncImagePainter(imageSrc) else (imageSrc as Painter),
            contentDescription = "Captured image",
            alpha = initialAlpha // change alpha for transparency
        )
    }

}
