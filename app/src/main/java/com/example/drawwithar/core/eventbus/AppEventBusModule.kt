package com.example.drawwithar.core.eventbus

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppEventBusModule {
    @Provides
    @Singleton
    fun provideAppEventBus(): AppEventBus {
        return AppEventBus()
    }
}

