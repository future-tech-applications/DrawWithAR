package com.example.drawwithar.util

import androidx.navigation.NavHostController

/**
 * Helper navigate function.
 */
fun NavHostController.navigateTo(
    dest: Any,
    removeCurrentPageOnPop: Boolean = false,
    savePageState: Boolean = false,
    restorePageState: Boolean = true,
    launchPageAsSingleTop: Boolean = true
) {
    navigate(dest as String) {
        graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = savePageState
                this.inclusive = removeCurrentPageOnPop
            }
        }
        launchSingleTop = launchPageAsSingleTop
        restoreState = restorePageState
    }
}




/**
 * To check if the current destination is the start destination of the graph.
 *
 * @param startRoute start route to match against the current destination route.
 * @return
 *
 */
fun NavHostController.isOnStartDestination(startRoute: String): Boolean {
    return currentBackStackEntry?.destination?.route == startRoute
}