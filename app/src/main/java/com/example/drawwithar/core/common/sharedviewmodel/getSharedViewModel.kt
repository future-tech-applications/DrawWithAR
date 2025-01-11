package com.example.drawwithar.core.common.sharedviewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.EntryPointAccessors


/**
* Get SharedViewModel for composable
*
* @return
*/
@Composable
fun getSharedViewModel(): SharedViewModel {
   val context = LocalContext.current.applicationContext
   val hiltEntryPoint = EntryPointAccessors.fromApplication(context, SharedViewModelEntryPoint::class.java)
   return hiltEntryPoint.getSharedViewModel()
}
