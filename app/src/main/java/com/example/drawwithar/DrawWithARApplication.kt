package com.example.drawwithar

import android.app.Application
//import com.google.firebase.FirebaseApp
//import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DrawWithARApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        // Initialize Firebase
//        FirebaseApp.initializeApp(this)
//
//        // Enable Crashlytics (optional, enabled by default)
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}
