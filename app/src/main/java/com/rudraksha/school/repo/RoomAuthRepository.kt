package com.rudraksha.school.repo

import com.rudraksha.school.database.dao.AppUserDao
import com.rudraksha.school.models.room.RoomAppUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomAuthRepository(private val appUserDao: AppUserDao) {

    // Check if the user exists and log them in
    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = appUserDao.getUserByEmailAndPassword(email, password)
            onResult(user != null)
        }
    }

    // Register a new user in Room
    fun register(appUser: RoomAppUser, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                appUserDao.insertUser(appUser)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    // Check if a user is already logged in (using the Room database)
    fun isLoggedIn(onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val isLoggedIn = appUserDao.getLoggedInUser() != null
            onResult(isLoggedIn)
        }
    }

    // Get the current logged-in user
    fun getCurrentUser(onResult: (RoomAppUser?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = appUserDao.getLoggedInUser()
            onResult(user)
        }
    }

    // Clear the logged-in user (used for logging out locally)
    fun logout(onComplete: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            appUserDao.getLoggedInUser()?.let { appUserDao.clearLoggedInUser(it) }
            onComplete()
        }
    }
}
