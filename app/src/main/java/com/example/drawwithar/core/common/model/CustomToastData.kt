package com.example.drawwithar.core.common.model

data class CustomToastData (
    val isVisible: Boolean = false,
    val message: String = "",
    val duration: Long = 2000L, // Duration in milliseconds
    val onDismiss: () -> Unit = {},
)