package com.example.drawwithar.core.common.model

import androidx.compose.runtime.Composable

data class BottomBarItemModel(
    val icon: @Composable (isSelected: Boolean) -> Unit,
    val label: String,
)