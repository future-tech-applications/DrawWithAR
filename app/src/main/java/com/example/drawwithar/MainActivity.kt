package com.example.drawwithar

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.example.compose.DrawWithARTheme
import com.example.drawwithar.core.navigation.ComposeScreenBackPressHandler
import com.example.drawwithar.core.navigation.DrawWithARNavHost
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize AppUpdateManager
        appUpdateManager = AppUpdateManagerFactory.create(this)

        // Check for updates
        checkForUpdates()

        // ask notification permissions
        askNotifcationPermission()

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.Transparent.toArgb()
            ),
        )
    }


    private fun checkForUpdates() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                Log.d("checkForUpdates", "Update Available!")
                // Force update is required, redirect to UpdateActivity
                startActivity(Intent(this, UpdateActivity::class.java))
                finish() // Close MainActivity
            } else {
                // No update required, proceed with the main app
                Log.d("checkForUpdates", "No Updates")
                startMainApp()
            }
        }
    }

    private fun startMainApp() {
        // Launch your main app UI here
        setContent {
            val navController = rememberNavController()
            // => On Hardware back button pressed logic
            ComposeScreenBackPressHandler(
                navController = navController,
                activity = this@MainActivity,
                backPressHandler = null
            )
            DrawWithARTheme {
                DrawWithARNavHost(navController = navController)
            }
        }
    }

    private fun askNotifcationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }

//    @Deprecated("This method has been deprecated in favor of using the OnBackPressedDispatcher ")
//    override fun onBackPressed() {
//        super.onBackPressed()
//        //finish()
//    }

}

