package com.example.drawwithar.core.eventbus

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.EntryPointAccessors


/**
* Get app event bus for composable
*
* @return
*/
@Composable
fun getAppEventBus(): AppEventBus {
   val context = LocalContext.current.applicationContext
   val hiltEntryPoint = EntryPointAccessors.fromApplication(context, AppEventBusEntryPoint::class.java)
   return hiltEntryPoint.appEventBus()
}
