@file:JvmName("BackPressHandlerImplForDrawingScreenKt")

package com.example.drawwithar.feature.drawingpage.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.drawwithar.core.navigation.ComposeScreenBackPressHandler
import com.example.drawwithar.feature.drawingpage.DrawingViewModel


/**
 * Back Press handler for Drawing Screen
 *
 * @param viewModel DrawingViewModel
 */
@Composable
fun DrawingScreenBackPressComposeHandler(viewModel: DrawingViewModel, navHostController: NavHostController) {
    val backPressHandler = BackPressHandlerImplForDrawingScreen(viewModel)
    ComposeScreenBackPressHandler(
        navController = navHostController,
        activity = navHostController.context as Activity,
        backPressHandler = backPressHandler
    )
}