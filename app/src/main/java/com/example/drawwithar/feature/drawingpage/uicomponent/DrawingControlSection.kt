package com.example.drawwithar.feature.drawingpage.uicomponent

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


@Composable
fun DrawingControlSection(
    viewModel: DrawingViewModel,
    items: List<BottomBarItemModel>,
    modifier: Modifier = Modifier,
    initiallyVisible: Boolean = true,
    isOpacitySliderVisible: Boolean = false,
    opacitySliderModel: OpacitySliderModel = OpacitySliderModel()
) {
    var isBottomBarVisible by rememberSaveable { mutableStateOf(initiallyVisible) }
    val isFlipActionBarVisible by viewModel.isFlipActionVisible.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.initializeDrawingControlItemStates(items.size)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // Opacity Slider
        OpacitySliderControl(
            modifier = modifier.padding(8.dp),
            opacitySliderModel = opacitySliderModel,
            isSliderVisible = isOpacitySliderVisible && isBottomBarVisible
        )

        FlipControlBar(
            isBarVisible = isBottomBarVisible && isFlipActionBarVisible,
            viewModel = viewModel
        )

        // Tip-Top Handler
        TipTopHandler(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 2.dp),
            isBarVisible = isBottomBarVisible,
            onToggleVisibility = { isBottomBarVisible = !isBottomBarVisible }
        )


        DrawingControlBottomBar(
            isBarVisible = isBottomBarVisible,
            viewModel = viewModel,
            items = items,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        )

    }
}

