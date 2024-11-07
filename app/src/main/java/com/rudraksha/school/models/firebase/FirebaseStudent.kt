package com.rudraksha.school.models.firebase

import com.rudraksha.school.models.room.RoomStudent

data class FirebaseStudent(
    val id: String,
    val rollNumber: String,
    val name: String,
    val standard: String,
    val section: String,
    val presentDays: Int,
    val subjectMarks: List<FirebaseSubject>
)

fun FirebaseStudent.toRoomStudent(): RoomStudent {
    return RoomStudent(
        id = id,
        rollNumber = rollNumber,
        name = name,
        standard = standard,
        section = section,
        presentDays = presentDays,
        subjectMarks = subjectMarks.map { it.toRoomSubject() }
    )
}