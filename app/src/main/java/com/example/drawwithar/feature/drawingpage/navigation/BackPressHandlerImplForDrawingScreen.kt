package com.example.drawwithar.feature.drawingpage.navigation

import androidx.navigation.NavHostController
import com.example.drawwithar.core.navigation.BackPressHandler
import com.example.drawwithar.feature.drawingpage.DrawingViewModel


class BackPressHandlerImplForDrawingScreen(
    private val viewModel: DrawingViewModel
) : BackPressHandler {

    // Handle specific back-press logic for this screen
    override fun handleBackPressForScreen(route: String, navController: NavHostController){
        viewModel.updateExitConfirmDialogOpened(true)
    }
}