package com.rudraksha.school.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rudraksha.school.models.room.RoomSubject

@Dao
interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: RoomSubject)

    @Query("SELECT * FROM subjects WHERE id = :subjectId")
    suspend fun getSubjectById(subjectId: Int): RoomSubject?

    @Query("SELECT * FROM subjects")
    suspend fun getAllSubjects(): List<RoomSubject>

    @Query("DELETE FROM SUBJECTS WHERE id = :id")
    suspend fun deleteSubject(id: String): Int

    @Query("DELETE FROM SUBJECTS")
    suspend fun deleteAllSubjects(): Int
}