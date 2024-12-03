package com.example.drawwithar

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.ui.theme.DrawWithARTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")

class MainActivity : ComponentActivity() {

    @OptIn(
        ExperimentalPermissionsApi::class,
        ExperimentalCoilApi::class,
        ExperimentalCoroutinesApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawWithARTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding).fillMaxSize())
                }
            }
        }
    }
}

