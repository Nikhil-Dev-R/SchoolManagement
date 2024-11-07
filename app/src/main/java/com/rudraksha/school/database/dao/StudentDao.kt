package com.rudraksha.school.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomSubject

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: RoomStudent)

    @Query("SELECT * FROM students WHERE id = :id")
    suspend fun getStudentById(id: String): RoomStudent?

    @Query("SELECT * FROM students")
    suspend fun getAllStudents(): List<RoomStudent>

    @Query("SELECT * FROM students WHERE standard = :standard")
    suspend fun getAllStudentsByStandard(standard: String): List<RoomStudent>

    @Query("SELECT * FROM students WHERE name LIKE '%' || :query || '%'")
    suspend fun getAllStudentsByName(query: String): List<RoomStudent>

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteStudent(id: String): Int

    @Query("DELETE FROM students")
    suspend fun deleteAllStudents(): Int

    @Query("UPDATE students SET name = :name, rollNumber = :rollNo, standard = :standard, section = :section, presentDays = :presentDays WHERE id = :id")
    suspend fun updateStudent(
        id: String, name: String, standard: String,
        rollNo: String, section: String, presentDays: Int
//        dob: String, gender: String, address: String,
    ): Int

    @Query("UPDATE students SET subjectMarks = :subjectMarks WHERE id = :id")
    suspend fun updateSubjectMarks(id: String, subjectMarks: List<RoomSubject>): Int
}
