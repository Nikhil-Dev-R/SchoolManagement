package com.rudraksha.school

import android.app.Application
import com.google.firebase.FirebaseApp

class SchoolApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}
