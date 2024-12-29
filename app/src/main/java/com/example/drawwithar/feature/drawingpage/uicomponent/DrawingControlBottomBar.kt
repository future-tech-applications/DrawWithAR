package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.example.drawwithar.core.common.model.BottomBarItemModel
import com.example.drawwithar.feature.drawingpage.model.OpacitySliderModel
import com.example.drawwithar.core.common.ui.components.BottomBarItem



@Composable
fun DrawingControlBottomBar(
    viewModel: DrawingViewModel,
    items: List<BottomBarItemModel>,
    modifier: Modifier = Modifier,
    initiallyVisible: Boolean = true,
    isOpacitySliderVisible: Boolean = false,
    opacitySliderModel: OpacitySliderModel = OpacitySliderModel()
) {
    var isBarVisible by rememberSaveable { mutableStateOf(initiallyVisible) }
    val itemStates by viewModel.drawingControlItemSelectedStates.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initializeDrawingControlItemStates(items.size)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // Opacity Slider
        OpacitySliderControl(
            modifier = modifier.padding(8.dp),
            opacitySliderModel = opacitySliderModel,
            isSliderVisible = isOpacitySliderVisible && isBarVisible
        )

        // Tip-Top Handler
        TipTopHandler(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 2.dp),
            isBarVisible = isBarVisible,
            onToggleVisibility = { isBarVisible = !isBarVisible }
        )
        AnimatedVisibility(
            visible = isBarVisible,
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
            Column(modifier = modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    items.forEachIndexed { index, item ->
                        BottomBarItem(
                            item = item,
                            isSelected = itemStates.getOrElse(index) { false },
                            onClick = {
                                viewModel.onDrawingControlItemSelected(index)
                            }
                        )
                    }
                }
            }
        }
    }
}

