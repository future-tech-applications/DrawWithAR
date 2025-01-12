package com.example.drawwithar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.compose.DrawWithARTheme
import com.example.drawwithar.core.common.ui.components.BorderedButton
import com.example.drawwithar.core.common.ui.components.CustomTopAppBar
import com.example.drawwithar.core.navigation.DrawWithARNavHost
import com.example.drawwithar.feature.drawingpage.DrawingScreen
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.example.drawwithar.feature.drawingpage.EMPTY_IMAGE_URI
import com.example.drawwithar.feature.homepage.HomeScreen
import com.example.drawwithar.feature.homepage.Screen
import com.example.drawwithar.feature.homepage.bottomnavigation.CustomBottomNavBar
import com.example.drawwithar.feature.homepage.bottomnavigation.getListOfBottomNavigationItems
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val drawingViewModel: DrawingViewModel by viewModels()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.Transparent.toArgb()
            ),
        )

        setContent {
            val navController = rememberNavController()
            DrawWithARTheme {
                DrawWithARNavHost(navController = navController, drawingViewModel = drawingViewModel)
            }
        }

    }

    @Deprecated("This method has been deprecated in favor of using the OnBackPressedDispatcher ")
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}

