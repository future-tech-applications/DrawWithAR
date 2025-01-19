package com.example.drawwithar.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DrawingEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drawingDao(): DrawingDao
}
