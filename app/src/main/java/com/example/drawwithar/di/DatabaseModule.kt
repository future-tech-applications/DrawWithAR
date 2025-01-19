package com.example.drawwithar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import android.content.Context
import androidx.room.Room
import com.example.drawwithar.room.AppDatabase
import com.example.drawwithar.room.DrawingDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "drawing_database"
        ).build()
    }

    @Provides
    fun provideDrawingDao(database: AppDatabase): DrawingDao {
        return database.drawingDao()
    }
}
