package com.example.drawwithar.feature.drawingpage.model

data class OpacitySliderModel (
    val alpha: Float = 0.5f,
    val isStartDrawing: Boolean = false,
    val onStartDrawing: () -> Unit = {},
    val onAlphaChange: (Float) -> Unit = {}
)