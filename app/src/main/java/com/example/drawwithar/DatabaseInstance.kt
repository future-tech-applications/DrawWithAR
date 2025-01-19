//package com.example.drawwithar
//
//import android.content.Context
//
//import androidx.room.Room
//import com.example.drawwithar.room.AppDatabase
//
//object DatabaseInstance {
//    @Volatile
//    private var INSTANCE: AppDatabase? = null
//
//    fun getDatabase(context: Context): AppDatabase {
//        return INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "drawing_database"
//            ).build()
//            INSTANCE = instance
//            instance
//        }
//    }
//}
