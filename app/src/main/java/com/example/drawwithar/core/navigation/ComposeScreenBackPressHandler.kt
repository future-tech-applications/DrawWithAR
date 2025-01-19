package com.example.drawwithar.core.navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.drawwithar.util.isOnStartDestination

/**
 * Compose Util function to handle hardware back press handler
 *
 * @param navController
 * @param activity
 */
@Composable
fun ComposeScreenBackPressHandler(
    navController: NavHostController,
    activity: Activity,
    backPressHandler: BackPressHandler?
) {
    val context = LocalContext.current
    // track the time of the last back press
    var lastBackPressedTime by remember { mutableLongStateOf(0L) }
    // the interval within which the second press will exit the app (e.g., 2000ms)
    val exitInterval = 2000L

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    DisposableEffect(Unit) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentRoute = navBackStackEntry?.destination?.route ?: ""

                // If back press handler is provided for any specific screen, handle it
                if(backPressHandler != null) {
                    backPressHandler.handleBackPressForScreen(currentRoute, navController)
                }
                // else generic backpress handling
                else {
                    // If reached on start route, exit the app
                    if (navController.isOnStartDestination(currentRoute)) {
                        val currentTime = System.currentTimeMillis()
                        // Exit the app if pressed again within the interval
                        if (currentTime - lastBackPressedTime < exitInterval) {
                            activity.finishAffinity()
                        } else {
                            // Else, show a prompt to press again to exit
                            Toast.makeText(
                                activity,
                                "Press back again to exit",
                                Toast.LENGTH_SHORT
                            ).show()
                            lastBackPressedTime = currentTime
                        }
                    } else {
                        navController.popBackStack()
                    }
                }

            }
        }
        backDispatcher?.addCallback(callback)
        onDispose { callback.remove() }
    }


}

