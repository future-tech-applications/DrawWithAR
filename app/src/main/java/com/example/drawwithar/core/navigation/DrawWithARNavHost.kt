package com.example.drawwithar.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.example.drawwithar.feature.drawingpage.navigation.drawingPageNavGraph
import com.example.drawwithar.feature.homepage.HomeViewModel
import com.example.drawwithar.feature.homepage.navigation.HOME_PAGE_GRAPH
import com.example.drawwithar.feature.homepage.navigation.HomePageRoutes
import com.example.drawwithar.feature.homepage.navigation.homePageNavGraph
import com.example.drawwithar.feature.savedrawingpage.navigation.saveDrawingPageNavGraph


/**
 * nav host for all application screens navigation
 */
@Composable
fun DrawWithARNavHost(
    navController: NavHostController
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        startDestination = HOME_PAGE_GRAPH,
    ) {
        homePageNavGraph(navController =  navController, viewModel = homeViewModel)
        drawingPageNavGraph(navController = navController)
        saveDrawingPageNavGraph(navController =  navController)
    }
}