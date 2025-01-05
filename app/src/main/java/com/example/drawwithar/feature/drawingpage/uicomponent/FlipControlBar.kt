package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drawwithar.R
import com.example.drawwithar.core.common.model.BottomBarItemModel
import com.example.drawwithar.core.common.ui.components.BottomBarItem
import com.example.drawwithar.feature.drawingpage.DrawingViewModel

@Composable
fun FlipControlBar(
    viewModel: DrawingViewModel,
    modifier: Modifier = Modifier,
    isBarVisible: Boolean = true,
) {

    val itemStates by viewModel.flipControlItemSelectedStates.collectAsState()
    val flipItems by viewModel.flipControlItems.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.initializeFlipControlItemStates(flipItems.size)
    }


    BottomHideAndShowAnimatedVisibility(isBottomBarVisible = isBarVisible) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .clip(
                    MaterialTheme.shapes.large
                )
            ,

            ) {
            Row(
                modifier = modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.surface),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                flipItems.forEachIndexed { index, item ->
                    BottomBarItem(
                        item = item,
                        isSelected = itemStates.getOrElse(index) { false },
                        onClick = {
                            viewModel.onFlipControlItemSelected(index)
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }

}