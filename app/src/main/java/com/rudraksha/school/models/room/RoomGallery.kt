package com.rudraksha.school.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gallery")
data class RoomGallery(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    val description: String
)
