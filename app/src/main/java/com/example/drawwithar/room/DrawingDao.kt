package com.example.drawwithar.room

import androidx.room.*

@Dao
interface DrawingDao {
    @Query("SELECT * FROM drawings WHERE isFavorite = 1")
    fun getFavorites(): List<DrawingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrawing(drawing: DrawingEntity)

    @Update
    suspend fun updateDrawing(drawing: DrawingEntity)

    @Delete
    suspend fun deleteDrawing(drawing: DrawingEntity)
}
