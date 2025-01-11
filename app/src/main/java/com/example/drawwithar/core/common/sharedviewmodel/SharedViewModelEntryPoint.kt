package com.example.drawwithar.core.common.sharedviewmodel

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
* SharedViewModel entry point
*
* @constructor Create empty SharedViewModel entry point
*/
@EntryPoint
@InstallIn(SingletonComponent::class)
interface SharedViewModelEntryPoint {
   /**
    * function to get shared view model
    *
    * @return
    */
   fun getSharedViewModel(): SharedViewModel
}
