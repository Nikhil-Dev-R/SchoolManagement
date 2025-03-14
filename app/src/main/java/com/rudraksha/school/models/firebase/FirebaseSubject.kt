package com.rudraksha.school.models.firebase

import com.rudraksha.school.models.room.RoomSubject

data class FirebaseSubject(
    val id: String = "",
    val name: String = "",
    val marks: Int = 0
)

data class Subject(
    val id: String = "",
    val name: String = "",
)

fun FirebaseSubject.toRoomSubject(): RoomSubject {
    return RoomSubject(
        id = this.id,
        name = this.name,
        marks = this.marks
    )
}
