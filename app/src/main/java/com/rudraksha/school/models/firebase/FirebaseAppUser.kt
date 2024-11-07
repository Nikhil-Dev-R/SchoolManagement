package com.rudraksha.school.models.firebase

import com.google.firebase.auth.FirebaseUser
import com.rudraksha.school.models.room.RoomAppUser

data class FirebaseAppUser(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val password: String = ""
)

fun FirebaseUser.toRoomAppUser(password: String): RoomAppUser {
    return RoomAppUser(
        id = uid,
        email = email ?: "",
        name = displayName ?: "",
        password = password
    )
}
