package com.example.drawwithar.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.example.drawwithar.EMPTY_IMAGE_URI

@Composable
fun DrawingControls(
    modifier: Modifier = Modifier,
    alpha: Float = 0.5f,
    isStartDrawing: Boolean = false,
    onStartDrawing: () -> Unit = {},
    onAlphaChange: (Float) -> Unit = {}
) {
    Column(
        modifier = modifier
        ,
    ) {
        Slider(
            value = alpha,
            onValueChange = { onAlphaChange(it) },
            valueRange = 0.1f..1.0f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // => Button to Enable AR and start Drawing button
        if(!isStartDrawing) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                ,
                onClick = {
                    onStartDrawing()
                }
            ) {
                val text = "Start Drawing"
                Text(text)
            }
        }
    }

}