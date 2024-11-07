package com.rudraksha.school.models.firebase

import com.rudraksha.school.models.room.RoomTeacher

data class FirebaseTeacher(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val isClassTeacher: Boolean = false,
    val standard: String = ""
)

fun FirebaseTeacher.toRoomTeacher(): RoomTeacher {
    return RoomTeacher(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        description = this.description,
        isClassTeacher = this.isClassTeacher,
        standard = this.standard
    )
}
