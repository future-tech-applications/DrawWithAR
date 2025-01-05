package com.example.drawwithar.util

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
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