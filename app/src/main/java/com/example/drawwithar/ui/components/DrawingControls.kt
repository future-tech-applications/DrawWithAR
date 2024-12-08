package com.example.drawwithar.ui.components

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drawwithar.EMPTY_IMAGE_URI
import com.example.drawwithar.R

@Composable
fun DrawingControls(
    modifier: Modifier = Modifier,
    alpha: Float = 0.5f,
    isStartDrawing: Boolean = false,
    onStartDrawing: () -> Unit = {},
    onAlphaChange: (Float) -> Unit = {}
) {

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 4.dp)
                .border(1.dp, Color.White, RoundedCornerShape(16.dp))
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(horizontal = 4.dp),
                painter = painterResource(id = R.drawable.baseline_opacity_24),
                tint = Color.Gray,
                contentDescription = "Opacity Low"
            )
            //Spacer(modifier = Modifier.weight(1f))
            Slider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = alpha,
                onValueChange = { onAlphaChange(it) },
                valueRange = 0.1f..1.0f,

            )
            Icon(
                modifier = Modifier.padding(horizontal = 4.dp),
                painter = painterResource(id = R.drawable.baseline_opacity_24),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Opacity High"
            )

        }

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