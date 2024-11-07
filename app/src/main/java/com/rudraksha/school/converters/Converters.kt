package com.rudraksha.school.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rudraksha.school.models.firebase.FirebaseSubject
import com.rudraksha.school.models.room.RoomSubject

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromRoomSubjectList(subjects: List<RoomSubject>?): String {
        return gson.toJson(subjects)
    }

    @TypeConverter
    fun toRoomSubjectList(subjectsString: String): List<RoomSubject> {
        val listType = object : TypeToken<List<RoomSubject>>() {}.type
        return gson.fromJson(subjectsString, listType)
    }

    @TypeConverter
    fun fromFirebaseSubjectList(subjects: List<FirebaseSubject>?): String {
        return gson.toJson(subjects)
    }

    @TypeConverter
    fun toFirebaseSubjectList(subjectsString: String): List<FirebaseSubject> {
        val listType = object : TypeToken<List<FirebaseSubject>>() {}.type
        return gson.fromJson(subjectsString, listType)
    }
}
