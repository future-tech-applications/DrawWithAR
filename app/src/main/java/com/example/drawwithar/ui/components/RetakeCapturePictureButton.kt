package com.example.drawwithar.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun RetakePictureButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit = {}
) {
    val contentPadding = PaddingValues(2.dp)
    OutlinedButton(
        modifier = modifier.clip(
            CircleShape
        )
        ,
        shape = CircleShape,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        contentPadding = contentPadding,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        onClick = { /* GNDN */ },
        enabled = false
    ) {
        Button(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .clip(CircleShape)
            ,
            shape = CircleShape,
            onClick = onClick
        ) {
            content()
        }
    }
}
