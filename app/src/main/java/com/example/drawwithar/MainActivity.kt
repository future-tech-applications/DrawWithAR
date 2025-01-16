package com.example.drawwithar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.example.compose.DrawWithARTheme
import com.example.drawwithar.core.navigation.DrawWithARNavHost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.Transparent.toArgb()
            ),
        )

        setContent {
            val navController = rememberNavController()
            DrawWithARTheme {
                DrawWithARNavHost(navController = navController)
            }
        }

    }

//    @Deprecated("This method has been deprecated in favor of using the OnBackPressedDispatcher ")
//    override fun onBackPressed() {
//        super.onBackPressed()
//        //finish()
//    }

}

