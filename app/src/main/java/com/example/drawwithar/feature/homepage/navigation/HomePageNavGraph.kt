package com.example.drawwithar.feature.homepage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.example.drawwithar.feature.drawingpage.DrawingViewModel
import com.example.drawwithar.feature.drawingpage.templates
import com.example.drawwithar.feature.homepage.HomeScreen
import com.example.drawwithar.feature.homepage.SeeAllPage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi


const val HOME_PAGE_GRAPH: String = "home_page_graph"

sealed class HomePageRoutes(val route: String) {
    data object HomePage : HomePageRoutes(route = "$HOME_PAGE_GRAPH/home_page")
    data object SeeAllPage : HomePageRoutes(route = "$HOME_PAGE_GRAPH/see_all_page/")
}

/**
 * NavGraph for Dashboard
 */

fun NavGraphBuilder.homePageNavGraph(
    drawingViewModel: DrawingViewModel,
    navController: NavHostController
) {
    navigation(startDestination = HomePageRoutes.HomePage.route, route = HOME_PAGE_GRAPH) {
        composable(
            HomePageRoutes.HomePage.route,
        ) {
            HomeScreen(drawingViewModel = drawingViewModel, navController)
        }

        composable(
            route = HomePageRoutes.SeeAllPage.route + "{title}", //"see_all_page/{title}"
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val imagesList = when (title) {
                "My Drawings" -> emptyList()
                "Templates" -> templates
                "Favorites" -> emptyList()
                else -> emptyList()

            }
                SeeAllPage(
                    drawingViewModel = drawingViewModel,
                    navController = navController,
                    title = title,
                    imagesList = imagesList
                )
        }
    }
}