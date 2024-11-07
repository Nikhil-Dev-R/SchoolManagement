package com.rudraksha.school.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class RoomAppUser(
    @PrimaryKey val id: String,
    val email: String,
    val name: String,
    val password: String,
)
