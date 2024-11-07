package com.rudraksha.school.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rudraksha.school.models.room.RoomAppUser

// UserDao.kt
@Dao
interface AppUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: RoomAppUser)

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: String): RoomAppUser?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, password: String): RoomAppUser?

    @Query("SELECT * FROM users LIMIT 1") // Get the first user to check if any user is logged in
    suspend fun getLoggedInUser(): RoomAppUser?

    @Delete
    suspend fun clearLoggedInUser(user: RoomAppUser)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}
