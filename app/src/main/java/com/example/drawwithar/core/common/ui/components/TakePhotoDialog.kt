package com.example.drawwithar.core.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.drawwithar.R

@Composable
fun TakePhotoDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onTakePhotoClick: () -> Unit,
    title: String
) {
    if (showDialog) {
        AlertDialog(
            modifier = modifier.wrapContentSize(),
            onDismissRequest = { /* Don't */ },
            containerColor = Color(0xFFFF4081),
            title = {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color(0xFFFF4081)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    // Title
                    Text(
                        text = "Take a photo of the resulting drawing for your album to track your progress",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.background,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.W400,
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Image
                    Image(
                        painter = painterResource(id = R.drawable.draw_with_ar_launcher_icon_removebg), // Replace with your illustration
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Subtitle (Link)
                    Text(
                        text = "It's not finished yet",
                        color = MaterialTheme.colorScheme.background,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.background,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable { /* Add link action here */ }
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    // Button
                    Button(
                        onClick = { onTakePhotoClick() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier.fillMaxWidth(0.6f)
                    ) {
                        Text(
                            text = "Take a photo",
                            color = Color(0xFFFF4081), // Button text color
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            },
            text = {},
            confirmButton = {},
            dismissButton = {},

            )


    }
}
