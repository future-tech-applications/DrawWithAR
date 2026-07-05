package com.example.drawwithar.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.firebase.crashlytics.FirebaseCrashlytics


/**
 * nav host for all application screens navigation
 */
@Composable
fun DrawWithARNavHost(
    navController: NavHostController
) {
    val crashlytics = FirebaseCrashlytics.getInstance()
    val homeViewModel = hiltViewModel<HomeViewModel>()
    // Track screen changes
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            val route = backStackEntry.destination.route ?: "unknown"
            crashlytics.setCustomKey("current_screen", route)
        }
    }

    NavHost(
        navController = navController,
        startDestination = HOME_PAGE_GRAPH,
    ) {
        homePageNavGraph(navController =  navController, viewModel = homeViewModel)
        drawingPageNavGraph(navController = navController)
        saveDrawingPageNavGraph(navController =  navController)
    }
}