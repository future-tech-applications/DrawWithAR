package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drawwithar.core.common.model.BottomBarItemModel
import com.example.drawwithar.core.common.ui.components.BottomBarItem
import com.example.drawwithar.feature.drawingpage.DrawingViewModel

@Composable
fun DrawingControlBottomBar(
    viewModel: DrawingViewModel,
    items: List<BottomBarItemModel>,
    modifier: Modifier = Modifier,
    isBarVisible: Boolean = true,
) {
    val itemStates by viewModel.drawingControlItemSelectedStates.collectAsState()

    BottomHideAndShowAnimatedVisibility(isBottomBarVisible = isBarVisible) {
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
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    }
