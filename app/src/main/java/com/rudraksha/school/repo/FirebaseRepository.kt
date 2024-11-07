package com.rudraksha.school.repo

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rudraksha.school.models.firebase.FirebaseGallery
import com.rudraksha.school.models.firebase.FirebaseStudent
import com.rudraksha.school.models.firebase.FirebaseTeacher

class FirebaseRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun saveStudent(student: FirebaseStudent) {
        database.child("students").child(student.id).setValue(student)
    }

    fun saveTeacher(teacher: FirebaseTeacher) {
        database.child("teachers").child(teacher.id).setValue(teacher)
    }

    fun saveGalleryItem(galleryItem: FirebaseGallery) {
        database.child("gallery").child(galleryItem.id).setValue(galleryItem)
    }

    // Fetch data from firebase
    fun fetchStudents(callback: (List<FirebaseStudent>) -> Unit) {
        database.child("students").get().addOnSuccessListener { dataSnapshot ->
            val students = mutableListOf<FirebaseStudent>()
            for (studentSnapshot in dataSnapshot.children) {
                val student = studentSnapshot.getValue(FirebaseStudent::class.java)
                student?.let { students.add(it) }
            }
            callback(students)
        }
    }

    fun fetchTeachers(callback: (List<FirebaseTeacher>) -> Unit) {
        database.child("teachers").get().addOnSuccessListener { dataSnapshot ->
            val teachers = mutableListOf<FirebaseTeacher>()
            for (teacherSnapshot in dataSnapshot.children) {
                val teacher = teacherSnapshot.getValue(FirebaseTeacher::class.java)
                teacher?.let { teachers.add(it) }
            }
            callback(teachers)
        }
    }

    fun fetchGalleryItems(callback: (List<FirebaseGallery>) -> Unit) {
        database.child("gallery").get().addOnSuccessListener { dataSnapshot ->
            val galleryItems = mutableListOf<FirebaseGallery>()
            for (gallerySnapshot in dataSnapshot.children) {
                val galleryItem = gallerySnapshot.getValue(FirebaseGallery::class.java)
                galleryItem?.let { galleryItems.add(it) }
            }
            callback(galleryItems)
        }
    }

    fun deleteAllStudents() {
        database.child("students").removeValue()
        Log.d("Firebase Student", "Deletion Successful")
    }

    fun deleteAllTeachers() {
        database.child("teachers").removeValue()
        Log.d("Firebase Teacher", "Deletion Successful")
    }

    fun deleteAllGalleryItems() {
        database.child("gallery").removeValue()
        Log.d("Firebase Gallery", "Deletion Successful")
    }
}
