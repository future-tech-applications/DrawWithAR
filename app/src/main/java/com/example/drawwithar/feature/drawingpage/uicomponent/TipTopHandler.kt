package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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

