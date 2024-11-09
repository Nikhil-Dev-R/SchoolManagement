package com.rudraksha.school.ui.screens

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")

    data object Classes : Screen("classes")
    data object ClassDesc : Screen("class_desc/{standard}") {
        fun createRoute(standard: String) = "class_desc/$standard"
    }
    data object Attendance : Screen("attendance/{standard}") {
        fun createRoute(standard: String) = "attendance/$standard"
    }
    data object Students : Screen("students/{standard}") {
        fun createRoute(standard: String) = "students/$standard"
    }
    data object AddStudent : Screen("add_student")
    data object EditStudent : Screen("edit_student/{id}")
    data object StudentProfile : Screen("student_profile/{id}")
    data object ResultManagement : Screen("result_management")

    data object Teachers : Screen("teachers")
    data object AddTeacher : Screen("add_teacher")
    data object EditTeacher : Screen("edit_teacher/{id}")
    data object TeacherProfile : Screen("teacher_profile/{id}")

    data object Gallery : Screen("gallery")
    data object AddGallery : Screen("add_gallery")
    data object EditGallery : Screen("edit_gallery/{id}")
    data object GalleryDesc : Screen("gallery_desc/{id}")
}