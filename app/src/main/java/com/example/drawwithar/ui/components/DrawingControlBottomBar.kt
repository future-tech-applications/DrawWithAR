package com.example.drawwithar.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clipToBounds
import com.example.drawwithar.MainViewModel
import com.example.drawwithar.R
import com.example.drawwithar.model.OpacitySliderModel

data class BottomBarItemModel(
    val icon: @Composable (isSelected: Boolean) -> Unit,
    val label: String,
)

@Composable
fun DrawingControlBottomBar(
    viewModel: MainViewModel,
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

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
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
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
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

@Composable
private fun BottomBarItem(
    item: BottomBarItemModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clipToBounds(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item.icon(isSelected)
        Text(
            text = item.label,
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun TipTopHandler(
    modifier: Modifier = Modifier,
    isBarVisible: Boolean,
    onToggleVisibility: () -> Unit
) {
    Box(
        modifier = modifier
            .size(40.dp) // Circular size
            .background(MaterialTheme.colorScheme.surface, CircleShape)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onToggleVisibility
            ),
        contentAlignment = Alignment.Center // Center the arrow
    ) {
        Icon(
            imageVector = if (isBarVisible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = if (isBarVisible) "Hide bar" else "Show bar",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}




fun getListOfControlItems(): List<BottomBarItemModel> {
    return listOf(
        BottomBarItemModel(
            icon = { isSelected ->
                Icon(
                    painter = painterResource(id = R.drawable.baseline_opacity_24),
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                    contentDescription = "Opacity"
                )
            },
            label = "Opacity"
        ),
        BottomBarItemModel(
            icon = { isSelected ->
                Icon(
                    painter = painterResource(id = R.drawable.baseline_flip_24),
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                    contentDescription = "Flip"
                )
            },
            label = "Flip"
        ),
        BottomBarItemModel(
            icon = { isSelected ->
                Icon(
                    painter = painterResource(id = R.drawable.baseline_flash_on_24),
                    tint = if (isSelected) Color(0xFFFFA000) else Color.Gray,
                    contentDescription = "Flashlight"
                )
            },
            label = "Flashlight"
        ),
        BottomBarItemModel(
            icon = { isSelected ->
                Icon(
                    painter = painterResource(id = R.drawable.baseline_lock_24),
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                    contentDescription = "Freeze"
                )
            },
            label = "Freeze"
        ),
        BottomBarItemModel(
            icon = { isSelected ->
                Icon(
                    painter = painterResource(id = R.drawable.baseline_replay_24),
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                    contentDescription = "Reset"
                )
            },
            label = "Reset"
        ),
    )
}
