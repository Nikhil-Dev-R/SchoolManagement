package com.rudraksha.school.navigation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rudraksha.school.ui.screens.LoginScreen
import com.rudraksha.school.viewmodel.SchoolViewModel
import com.rudraksha.school.ui.screens.Screen
import com.rudraksha.school.ui.screens.StudentProfileScreen
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rudraksha.school.models.standardList
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomTeacher
import com.rudraksha.school.ui.screen.ResultManagementScreen
import com.rudraksha.school.ui.screens.AttendanceScreen
import com.rudraksha.school.ui.screens.ClassDescScreen
import com.rudraksha.school.ui.screens.ClassesScreen
import com.rudraksha.school.ui.screens.GalleryDescScreen
import com.rudraksha.school.ui.screens.GalleryScreen
import com.rudraksha.school.ui.screens.HomeScreen
import com.rudraksha.school.ui.screens.RegisterScreen
import com.rudraksha.school.ui.screens.SplashScreen
import com.rudraksha.school.ui.screens.TeacherProfileScreen
import com.rudraksha.school.ui.screens.TeachersScreen
import com.rudraksha.school.ui.utils.validateLoginInput
import com.rudraksha.school.ui.utils.validateRegistrationInput

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    context: Context,
    schoolViewModel: SchoolViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by schoolViewModel.uiState.collectAsState() // Use collectAsState to observe the StateFlow

    LaunchedEffect(Unit) {
//        schoolViewModel.eraseAllData()
        if(schoolViewModel.getCurrentUser() != null)
            schoolViewModel.loadInitialData()
        schoolViewModel.fetchStudents()
        schoolViewModel.fetchTeachers()
        schoolViewModel.fetchGalleryItems()
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navigateTo = {
                    if(schoolViewModel.isLoggedIn()) {
                        navController.navigate(
                            route = Screen.Home.route
                        ) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                    else {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                },
                modifier = modifier,
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = { email, password ->
                    validateLoginInput(uiState, email, password)
                    if (uiState.errorMessage != "") {
                        Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                    } else {
                        schoolViewModel.login(
                            email = email,
                            password = password
                        ) { success ->
                            if (success) {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Splash.route) { inclusive = true }
                                }
                                Log.d("Login", "Success")
//                                Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.d("Login", "Failure ${uiState.errorMessage}")
//                                Toast.makeText(context, "Login Failed! ${uiState.errorMessage}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    schoolViewModel.reset()
                },
                modifier = modifier
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = { name, email, password, confirmPassword ->
                    validateRegistrationInput(uiState, name, email, password, confirmPassword)
                    if (uiState.errorMessage != "") {
                        Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                    } else {
                        schoolViewModel.register(
                            email = email,
                            password = password
                        ) { success ->
                            if (success) {
                                navController.navigateUp()
//                                (Screen.Home.route) {
//                                    popUpTo(Screen.Home.route) { inclusive = true }
//                                }
                                Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Registration Failed! ${uiState.errorMessage}",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    schoolViewModel.reset()
                },
                modifier = modifier
            )
        }

        composable(
            route = Screen.Home.route,
        ) {
            val currentUser = schoolViewModel.getCurrentUser()
            HomeScreen(
                name = currentUser?.displayName ?: "User",
                email = currentUser?.email ?: "Email",
                modifier = modifier,
                signOut = { schoolViewModel.signOut() },
                navController = navController
            )
        }

        /*composable(route = Screen.Admission.route) {
            Adm
        }*/

        composable(Screen.Classes.route) {
            ClassesScreen(
                classList = standardList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onCardClick = { standard ->
                    schoolViewModel.fetchStudentsByStandard(standard = standard)
                    navController.navigate("${Screen.ClassDesc.route}/$standard")
                },
                modifier = modifier
            )
        }

        composable(
            route = "${Screen.ClassDesc.route}/{standard}",
            arguments = listOf(navArgument("standard") { type = NavType.StringType })
        ) { backStackEntry ->
            val standard = backStackEntry.arguments?.getString("standard") ?: standardList[0]
            val studentList = uiState.studentList.distinct().filter { it.standard == standard }.sortedBy { it.rollNumber }
            val subjectList = studentList.firstOrNull()?.subjectMarks?.sortedBy { it.id } ?: emptyList()
            val classTeacher = uiState.teacherList.distinct().find { it.standard == standard }?.name ?: "Unavailable"

            ClassDescScreen(
                standard = standard,
                classTeacher = classTeacher,
                studentList = studentList,
                subjectList = subjectList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onStudentClick = { id ->
                    navController.navigate("${Screen.StudentProfile.route}/${id}")
                },
                onAttendanceClick = { standard ->
                    Log.d("Navigation", "Navigating to Attendance Screen with grade level: $standard")
                    navController.navigate("${Screen.Attendance.route}/$standard")
                },
                modifier = modifier
            )
        }

        composable(
            route = "${Screen.StudentProfile.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val student = uiState.studentList.find { it.id == id } ?: RoomStudent(
                id = "",
                rollNumber = "",
                name = "",
                standard = "",
                section = "",
                presentDays = 0,
                subjectMarks = emptyList()
            )
            StudentProfileScreen(
                student = student,
                onNavIconClick = { navController.navigateUp() },
                onResultClick = {
                    navController.navigate("${Screen.ResultManagement.route}/${id}")
                },
                modifier = modifier
            )
        }

        composable(
            route = "${Screen.Attendance.route}/{standard}",
            arguments = listOf(navArgument("standard") { type = NavType.StringType }),
        ) { backStackEntry ->
            val gradeLevel = backStackEntry.arguments?.getString("standard") ?: standardList[0]
            val studentList = uiState.studentList.distinct().filter { it.standard == gradeLevel }.sortedBy { it.rollNumber }

            AttendanceScreen(
                studentList = studentList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onSubmitClick = { attendanceMap ->
                    schoolViewModel.updateAttendance(gradeLevel, attendanceMap)
                    Log.d("Attendance", "Submitted Attendance: ${attendanceMap.size}")
                    navController.navigateUp()
                },
                modifier = modifier
            )
        }

        composable(
            route = "${Screen.ResultManagement.route}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val student = uiState.studentList.find { it.id == id } ?: RoomStudent(
                id = "",
                rollNumber = "",
                name = "",
                standard = "",
                section = "",
                presentDays = 0,
                subjectMarks = emptyList()
            )
            ResultManagementScreen(
                student = student,
                context = context,
                navController = navController,
                modifier = modifier
            )
        }

        composable(Screen.Teachers.route) {
//            Log.d("Teacher Size", "Teachers Route: ${uiState.teacherList.size}")
            TeachersScreen(
                teacherList = uiState.teacherList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onCardClick = { id ->
                    navController.navigate("${Screen.TeacherProfile.route}/${id}")
                },
                modifier = modifier
            )
        }

        composable(
            route = "${Screen.TeacherProfile.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            Log.d("Teacher Id", "Received $id")
            val teacher = uiState.teacherList.find { it.id == id } ?: RoomTeacher(
                id = "",
                name = "",
                imageUrl = "",
                description = "",
                isClassTeacher = false,
                standard = ""
            )
            TeacherProfileScreen(
                teacher = teacher,
                onNavIconClick = { navController.navigateUp() },
                onClassClick = { gradeLevel ->
                    navController.navigate("${Screen.ClassDesc.route}/${gradeLevel}")
                },
                modifier = modifier
            )
        }

        composable(Screen.Gallery.route) {
            GalleryScreen(
                galleryItems = uiState.galleryList,
                onNavIconClick = { navController.navigateUp() },
                onItemClick = { id ->
                    navController.navigate("${Screen.GalleryDesc.route}/$id")
                },
                modifier = modifier
            )
        }

        composable(
            route = "${Screen.GalleryDesc.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val galleryItem = uiState.galleryList.find { it.id == id }
            if (galleryItem != null) {
                GalleryDescScreen(
                    galleryItem = galleryItem,
                    onNavIconClick = { navController.navigateUp() },
                    modifier = modifier
                )
            }
        }
    }

    // Handle UI States
    if (uiState.isLoading) {
        // Show a loading spinner or overlay
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(modifier = Modifier)
        }
    }

    if (uiState.isError) {
        // Show an error message
        Toast.makeText(context, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show()
    }
}