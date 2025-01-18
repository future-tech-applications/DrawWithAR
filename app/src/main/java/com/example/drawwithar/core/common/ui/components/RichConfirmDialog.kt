package com.example.drawwithar.core.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.drawwithar.R

@Composable
fun RichConfirmDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    title: String,
    imageSrc: Int? = null,
    dialogColor: Color = Color(0xFFFF4081),
    dismissText: String = "",
    confirmText: String = "",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            modifier = modifier.wrapContentSize(),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = { onDismiss() },
            containerColor = dialogColor,
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().align(Alignment.End),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier
                                .clickable { onDismiss() }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(dialogColor)
                            .align(Alignment.CenterHorizontally)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Title
                        Text(
                            text = title,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.background,
                            fontWeight = FontWeight.W400,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Image
                        if(imageSrc != null) {
                            Image(
                                painter = painterResource(id = imageSrc),
                                contentDescription = null,
                                modifier = Modifier.size(150.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Dismiss Text
                        if(dismissText.isNotEmpty()) {
                            Text(
                                text = dismissText,
                                color = MaterialTheme.colorScheme.background,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.CenterHorizontally)
                                    .clickable { onDismiss() }
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                        // Confirm Button
                        if(confirmText.isNotEmpty()) {
                            Button(
                                onClick = {
                                    onDismiss()
                                    onConfirm()
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                modifier = Modifier.fillMaxWidth(0.6f)
                            ) {
                                Text(
                                    text = confirmText,
                                    color = dialogColor, // Button text color
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }

                    }
                }
            },
            text = {},
            confirmButton = {},
            dismissButton = {},

            )


    }
}
