package com.example.drawwithar.core.common.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun ConfirmationDialog(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit = {}
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { /* Don't */ },
        title = {
            Text(text = title)
        },
        text = {
            Text(text)
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Ok")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        },

    )
}