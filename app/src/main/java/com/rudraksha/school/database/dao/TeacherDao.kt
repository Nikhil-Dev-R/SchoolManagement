package com.rudraksha.school.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomTeacher

@Dao
interface TeacherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacher: RoomTeacher)

    @Query("SELECT * FROM teachers WHERE id = :id")
    suspend fun getTeacherById(id: String): RoomTeacher?

    @Query("SELECT * FROM teachers")
    suspend fun getAllTeachers(): List<RoomTeacher>

    @Query("DELETE FROM teachers WHERE id = :id")
    suspend fun deleteTeacher(id: String): Int

    @Query("DELETE FROM teachers")
    suspend fun deleteAllTeachers(): Int

    @Query("UPDATE teachers SET name = :name, isClassTeacher = :isClassTeacher, standard = :gradeLevel, description = :description WHERE id = :id")
    suspend fun updateTeacher(
        id: String, name: String, description:
        String, isClassTeacher: Boolean, gradeLevel: String
    ): Int
}
