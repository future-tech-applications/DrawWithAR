package com.example.drawwithar.core.navigation
import androidx.navigation.NavHostController
interface BackPressHandler {
    /**
     * Handle specific back-press logic for a screen     *
     * @param route
     * @param navController
     */
    fun handleBackPressForScreen(route: String, navController: NavHostController)
}