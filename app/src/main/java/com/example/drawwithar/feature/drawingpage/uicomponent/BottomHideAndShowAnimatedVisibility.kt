package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

@Composable
fun BottomHideAndShowAnimatedVisibility(
    isBottomBarVisible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isBottomBarVisible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 400),
            initialOffsetY = { it } // Slide in from below
        ) + fadeIn(animationSpec = tween(durationMillis = 400)),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 400),
            targetOffsetY = { it } // Slide out below
        ) + fadeOut(animationSpec = tween(durationMillis = 400))
    )
    {
        content()
    }


}
