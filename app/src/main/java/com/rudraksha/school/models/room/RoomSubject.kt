package com.rudraksha.school.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rudraksha.school.models.firebase.FirebaseSubject

@Entity(tableName = "subjects")
data class RoomSubject(
    @PrimaryKey val id: String,
    val name: String,
    val marks: Int
)

fun RoomSubject.toFirebaseSubject(): FirebaseSubject {
    return FirebaseSubject(
        id = id,
        name = name,
        marks = marks
    )
}