package com.rudraksha.school.models.firebase

import com.rudraksha.school.models.room.RoomGallery

data class FirebaseGallery(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String
)

fun FirebaseGallery.toRoomGallery(): RoomGallery {
    return RoomGallery(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description
    )
}
