package com.example.drawwithar.feature.drawingpage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.feature.drawingpage.DrawingScreen
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.hilt.navigation.compose.hiltViewModel


const val DRAWING_PAGE_GRAPH: String = "drawing_page_graph"

sealed class DrawingPageRoutes(val route: String) {
    data object DrawingPage : DrawingPageRoutes(route = "$DRAWING_PAGE_GRAPH/drawing_page")
}

/**
 * NavGraph for Dashboard
 */
@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoilApi::class,
    ExperimentalCoroutinesApi::class
)
fun NavGraphBuilder.drawingPageNavGraph(
    navController: NavHostController,
    viewModel: DrawingViewModel
) {
    navigation(startDestination = DrawingPageRoutes.DrawingPage.route, route = DRAWING_PAGE_GRAPH) {
        composable(
            DrawingPageRoutes.DrawingPage.route,
        ) {

            DrawingScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

    }
}