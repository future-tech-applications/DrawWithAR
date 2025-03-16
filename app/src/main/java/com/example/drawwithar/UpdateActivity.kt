package com.example.drawwithar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

class UpdateActivity : ComponentActivity() {

    private lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("UpdateActivity", "onCreate called")
        // Initialize AppUpdateManager
        appUpdateManager = AppUpdateManagerFactory.create(this)

        // Check for updates
        checkForUpdates()
    }

    private fun checkForUpdates() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Force update is required, redirect to Play Store
                redirectToPlayStore()
            } else {
                // No update required, close this activity and proceed to the main app
                Log.d("UpdateActivity", "No Updates")
                finish()
            }
        }
    }

    private fun redirectToPlayStore() {
        val appPackageName = packageName
        try {
            Log.d("UpdateActivity", "Redirecting to Play Store")
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (e: Exception) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
        }
        finish() // Close the activity after redirecting
    }
}