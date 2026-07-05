package com.example.drawwithar

import android.app.Application
//import com.google.firebase.FirebaseApp
//import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DrawWithARApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //throw RuntimeException("Test Crash: This is a test crash for Firebase Crashlytics")
        // test comment


//        // Initialize Firebase
//        FirebaseApp.initializeApp(this)
//
//        // Enable Crashlytics (optional, enabled by default)
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}
