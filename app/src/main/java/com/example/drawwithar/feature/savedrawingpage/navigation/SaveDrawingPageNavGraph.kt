package com.example.drawwithar.feature.savedrawingpage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.feature.savedrawingpage.SaveDrawingCameraScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi


const val SAVE_DRAWING_PAGE_GRAPH: String = "save_drawing_page_graph"

sealed class SaveDrawingPageRoutes(val route: String) {
    data object SaveDrawingCameraPage : SaveDrawingPageRoutes(route = "$SAVE_DRAWING_PAGE_GRAPH/camera_page")
    data object SaveDrawingReviewPage : SaveDrawingPageRoutes(route = "$SAVE_DRAWING_PAGE_GRAPH/review_page")
}

/**
 * NavGraph for Dashboard
 */

@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoilApi::class,
    ExperimentalCoroutinesApi::class
)
fun NavGraphBuilder.saveDrawingPageNavGraph(
    navController: NavHostController
) {
    navigation(startDestination = SaveDrawingPageRoutes.SaveDrawingCameraPage.route, route = SAVE_DRAWING_PAGE_GRAPH) {
        composable(
            SaveDrawingPageRoutes.SaveDrawingCameraPage.route,
        ) {
            SaveDrawingCameraScreen(navController)
        }

    }
}