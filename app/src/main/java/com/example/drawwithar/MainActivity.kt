package com.example.drawwithar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import coil.annotation.ExperimentalCoilApi
import com.example.compose.DrawWithARTheme
import com.example.drawwithar.feature.drawingpage.DrawingScreen
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<DrawingViewModel>()

    @OptIn(
        ExperimentalPermissionsApi::class,
        ExperimentalCoilApi::class,
        ExperimentalCoroutinesApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color(0xFF870046).toArgb(),
                Color(0xFF4B0024).toArgb()
            ),
        )
        setContent {
            DrawWithARTheme {
                DrawingScreen(viewModel)
            }
        }
    }
}

