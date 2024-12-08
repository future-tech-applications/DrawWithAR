package com.example.drawwithar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BorderedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.White,
    borderCornerRadius: Dp = 8.dp,
    borderShape: RoundedCornerShape = RoundedCornerShape(borderCornerRadius),
    buttonElevation: Dp = 1.dp

) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(color = Color.Transparent)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = borderShape
            )
            .width(100.dp)
            .height(35.dp)
        ,
        shape = borderShape,
        elevation = ButtonDefaults.buttonElevation(buttonElevation)
    ) {
        Text(
            text = text,
            modifier = Modifier
        )
    }
}
