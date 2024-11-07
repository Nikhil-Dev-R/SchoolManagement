package com.rudraksha.school.repo

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.coroutineScope

class FirebaseAuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful)
            }
    }

    suspend fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        coroutineScope {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    onResult(task.isSuccessful)
                }
        }
    }

    fun getCurrentUser(): com.google.firebase.auth.FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}
