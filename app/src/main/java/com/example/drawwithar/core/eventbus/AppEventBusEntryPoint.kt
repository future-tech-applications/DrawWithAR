package com.example.drawwithar.core.eventbus

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
* App event bus entry point
*
* @constructor Create empty App event bus entry point
*/
@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppEventBusEntryPoint {
   /**
    * function to get app event bus
    *
    * @return
    */
   fun appEventBus(): AppEventBus
}
