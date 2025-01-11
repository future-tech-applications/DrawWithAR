package com.example.drawwithar.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.drawwithar.feature.drawingpage.DrawingScreen
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.example.drawwithar.feature.drawingpage.navigation.DRAWING_PAGE_GRAPH
import com.example.drawwithar.feature.drawingpage.navigation.drawingPageNavGraph
import com.example.drawwithar.feature.homepage.navigation.HOME_PAGE_GRAPH
import com.example.drawwithar.feature.homepage.navigation.homePageNavGraph


/**
 * nav host for all application screens navigation
 */
@Composable
fun DrawWithARNavHost(
    navController: NavHostController,
    drawingViewModel: DrawingViewModel
) {
    NavHost(
        navController = navController,
        startDestination = HOME_PAGE_GRAPH,
    ) {
        homePageNavGraph(navController =  navController)
        drawingPageNavGraph(navController = navController, viewModel = drawingViewModel)

    }
}