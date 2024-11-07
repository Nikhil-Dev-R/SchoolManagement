package com.rudraksha.school.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teachers")
data class RoomTeacher(
    @PrimaryKey val id: String,
    val name: String,
    var imageUrl: String = "https://www.pngitem.com/pimgs/m/78-786293_1240-x-1240-0-avatar-profile-icon-png.png",
    var description: String = "",
    var isClassTeacher: Boolean = false,
    var standard: String = ""
)
