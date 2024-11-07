package com.rudraksha.school.ui.screens

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object Admission : Screen("admission")

    data object Classes : Screen("classes")
    data object ClassDesc : Screen("class_desc/{gradeLevel}") {
        fun createRoute(gradeLevel: String) = "class_desc/$gradeLevel"
    }
    data object Attendance : Screen("attendance/{gradeLevel}") {
        fun createRoute(gradeLevel: String) = "attendance/$gradeLevel"
    }
    data object StudentProfile : Screen("student_profile/{id}") {
        fun createRoute(id: String) = "student_profile/$id"
    }
    data object ResultManagement : Screen("result_management")

    data object Teachers : Screen("teachers")
    data object TeacherProfile : Screen("teacher_profile/{id}") {
        fun createRoute(id: String) = "teacher_profile/$id"
    }

    data object Gallery : Screen("gallery")
    data object GalleryDesc : Screen("gallery_desc/{id}") {
        fun createRoute(id: String) = "gallery_desc/$id"
    }
}