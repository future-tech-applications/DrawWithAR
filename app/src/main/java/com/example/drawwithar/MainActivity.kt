package com.example.drawwithar

import android.os.Bundle
import androidx.activity.ComponentActivity
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
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color(0xFF870046).toArgb(),
                Color(0xFF4B0024).toArgb()
            ),
        )
        setContent {
            val navController = rememberNavController()

            DrawWithARTheme {
                DrawWithARNavHost(navController = navController)
//                Scaffold(
//                    topBar = {
//                        CustomTopAppBar(
//                            title =  "Draw with AR",
//                            onBackPressed = {  },
//                            isShowBackBtn = true,
//                            actions = {
//                                // => Button to Start or Finish Drawing
//                                if(imageUri!= EMPTY_IMAGE_URI) {
//                                    BorderedButton(
//                                        text = if(isStartDrawing) "Finish" else "Start",
//                                        onClick = {
//                                            viewModel.toggleDrawing()
//                                            viewModel.resetDrawingImageStates()
//                                        }
//                                    )
//                                }
//                            }
//                        )
//                    },
//                    bottomBar = {
//                        if (true) {
//                            CustomBottomNavBar(navController = navController, bottomNavigationItems = getListOfBottomNavigationItems())
//                        }
//                    },
//
//
//                )
//                { innerPadding ->
//                    Screen(modifier = Modifier.padding(innerPadding), name="Home")
//                    //DrawingScreen(viewModel, innerPadding)
//                }

            }
        }
    }
}

