package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drawwithar.R
import com.example.drawwithar.core.common.model.BottomBarItemModel

@OptIn(ExperimentalMaterial3Api::class)
fun getListOfBottomControlItems(): List<BottomBarItemModel> {
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


fun getListOfFlipActionItems(): List<BottomBarItemModel> {
    return listOf(
        BottomBarItemModel(

            icon = { isSelected ->
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.baseline_flip_24),
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                    contentDescription = "Flip Horizontal"
                )
            },
            label = "Horizontal"
        ),
        BottomBarItemModel(
            icon = { isSelected ->
                Icon(
                    modifier = Modifier.size(20.dp).rotate(90f),
                    painter = painterResource(id = R.drawable.baseline_flip_24),
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                    contentDescription = "Flip Vertical"
                )
            },
            label = "Vertical"
        )
    )
}
