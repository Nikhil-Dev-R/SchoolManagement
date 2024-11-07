package com.rudraksha.school.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rudraksha.school.models.firebase.FirebaseStudent
import com.rudraksha.school.models.firebase.toRoomSubject

@Entity(tableName = "students")
data class RoomStudent(
    @PrimaryKey val id: String,
    val rollNumber: String,
    val name: String,
    val standard: String,
    val section: String,
    val presentDays: Int,
    val subjectMarks: List<RoomSubject>
)

fun RoomStudent.toFirebaseStudent(): FirebaseStudent {
    return FirebaseStudent(
        id = id,
        rollNumber = rollNumber,
        name = name,
        standard = standard,
        section = section,
        presentDays = presentDays,
        subjectMarks = subjectMarks.map { it.toFirebaseSubject() }
    )
}