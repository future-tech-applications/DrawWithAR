package com.example.drawwithar.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drawings")
data class DrawingEntity(
    @PrimaryKey val uri: String,
    val isFavorite: Boolean
)
