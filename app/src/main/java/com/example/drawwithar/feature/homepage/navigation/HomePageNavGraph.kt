package com.example.drawwithar.feature.homepage.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.drawwithar.core.common.ui.components.HomeSections
import com.example.drawwithar.feature.drawingpage.templates
import com.example.drawwithar.feature.homepage.HomeScreen
import com.example.drawwithar.feature.homepage.HomeViewModel
import com.example.drawwithar.feature.homepage.SeeAllPage


const val HOME_PAGE_GRAPH: String = "home_page_graph"

sealed class HomePageRoutes(val route: String) {
    data object HomePage : HomePageRoutes(route = "${HOME_PAGE_GRAPH}/home_page")
    data object SeeAllPage : HomePageRoutes(route = "${HOME_PAGE_GRAPH}/see_all_page/")
}

/**
 * NavGraph for Dashboard
 */

fun NavGraphBuilder.homePageNavGraph(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    navigation(startDestination = HomePageRoutes.HomePage.route, route = HOME_PAGE_GRAPH) {
        composable(
            HomePageRoutes.HomePage.route,
        ) {
            LaunchedEffect(Unit) {
                viewModel.fetchSavedImages(navController.context)
            }
            HomeScreen(navController, viewModel)
        }

        composable(
            route = HomePageRoutes.SeeAllPage.route + "{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""

            val imagesList: List<Any> = when (title) {
                HomeSections.MyDrawings.title -> {
                        viewModel.savedDrawingsList.collectAsState().value.reversed()
                }
                HomeSections.Templates.title -> templates
                HomeSections.Favorites.title -> emptyList()
                else -> emptyList()

            }
                SeeAllPage(
                    navController = navController,
                    title = title,
                    imagesList = imagesList
                )
        }
    }
}
