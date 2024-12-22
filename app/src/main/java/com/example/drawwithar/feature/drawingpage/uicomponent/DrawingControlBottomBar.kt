package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
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
    var isBarVisible by remember { mutableStateOf(initiallyVisible) }
    val itemStates by viewModel.drawingControlItemStates.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initializeDrawingControlItemStates(items.size)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // Opacity Slider
        OpacitySliderControl(
            modifier = modifier.padding(8.dp),
            opacitySliderModel = opacitySliderModel,
            isSliderVisible = isOpacitySliderVisible
        )
        // Tip-Top Handler
        TipTopHandler(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 2.dp),
            isBarVisible = true,
            onToggleVisibility = { isBarVisible = !isBarVisible }
        )
        AnimatedVisibility(
            visible = isBarVisible,
            enter = androidx.compose.animation.fadeIn(animationSpec = tween(300)),
            exit = androidx.compose.animation.fadeOut(animationSpec = tween(300))
        ) {
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

