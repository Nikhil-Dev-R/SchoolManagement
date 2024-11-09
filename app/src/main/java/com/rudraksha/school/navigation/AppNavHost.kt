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
import androidx.compose.runtime.SideEffect
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
import com.rudraksha.school.models.firebase.FirebaseStudent
import com.rudraksha.school.models.firebase.FirebaseTeacher
import com.rudraksha.school.models.standardList
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomTeacher
import com.rudraksha.school.ui.screen.ResultManagementScreen
import com.rudraksha.school.ui.screens.AddStudentScreen
import com.rudraksha.school.ui.screens.AddTeacherScreen
import com.rudraksha.school.ui.screens.AttendanceScreen
import com.rudraksha.school.ui.screens.ClassDescScreen
import com.rudraksha.school.ui.screens.ClassesScreen
import com.rudraksha.school.ui.screens.GalleryDescScreen
import com.rudraksha.school.ui.screens.GalleryScreen
import com.rudraksha.school.ui.screens.HomeScreen
import com.rudraksha.school.ui.screens.RegisterScreen
import com.rudraksha.school.ui.screens.SplashScreen
import com.rudraksha.school.ui.screens.StudentsScreen
import com.rudraksha.school.ui.screens.TeacherProfileScreen
import com.rudraksha.school.ui.screens.TeachersScreen
import com.rudraksha.school.ui.utils.validateLoginInput
import com.rudraksha.school.ui.utils.validateRegistrationInput
import com.rudraksha.school.ui.utils.validateTeacherDetailsInput

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
    }

    LaunchedEffect (uiState.anyMessage) {
        // Show an error message
        if(uiState.toastMessage.isNotEmpty()) {
            Toast.makeText(context, uiState.toastMessage, Toast.LENGTH_SHORT).show()
            schoolViewModel.reset()
        }
    }
//    if (schoolViewModel.isNetworkAvailable(context)) {
//        schoolViewModel.saveStudent(
//            FirebaseStudent(
//
//            )
//        )
//    }

//    @Composable
//    fun showToast() {
        if (uiState.isLoading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier)
            }
        }
//    }

//    LaunchedEffect(uiState.anyMessage) {
//        showToast()
//    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            var quote = schoolViewModel.getQuote()
            SideEffect {
                quote = schoolViewModel.getQuote()
            }
            SplashScreen(
                quote = quote,
                navigateTo = {
                    if(schoolViewModel.isLoggedIn()) {
                        navController.navigate(Screen.Home.route) {
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
                    schoolViewModel.login(
                        email = email,
                        password = password
                    ) { success ->
                        if (success) {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Splash.route) { inclusive = true }
                            }
                            Log.d("Login", "Success")
                            uiState.anyMessage = true
                            uiState.toastMessage = "Login Successful!"
                        } else {
                            Log.d("Login", "Failure ${uiState.toastMessage}")
                            uiState.anyMessage = true
                            uiState.toastMessage = "Login Failed! ${uiState.toastMessage}"
                        }
                    }
                },
                modifier = modifier
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = { name, email, password, confirmPassword ->
                    validateRegistrationInput(uiState, name, email, password, confirmPassword)
                    if (uiState.toastMessage != "") {
                        Toast.makeText(context, uiState.toastMessage, Toast.LENGTH_SHORT).show()
                    } else {
                        schoolViewModel.register(
                            email = email,
                            password = password
                        ) { success ->
                            if (success) {
                                navController.navigateUp()
                                uiState.toastMessage = "Registration Successful!"
                            } else {
                                uiState.toastMessage = "Registration Failed! ${uiState.toastMessage}"
                            }
                        }
                    }
                    schoolViewModel.reset()
                },
                modifier = modifier
            )
        }

        composable(Screen.Home.route) {
            val currentUser = schoolViewModel.getCurrentUser()
            HomeScreen(
                name = currentUser?.displayName ?: "User",
                email = currentUser?.email ?: "Email",
                signOut = { schoolViewModel.signOut() },
                navController = navController
            )
        }

        composable(Screen.Classes.route) {
            ClassesScreen(
                classList = standardList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onCardClick = { standard ->
                    navController.navigate("${Screen.ClassDesc.route}/$standard")
                },
                modifier = modifier
            )
        }

        composable(
            route = "${Screen.ClassDesc.route}/{standard}",
            arguments = listOf(navArgument("standard") { type = NavType.StringType })
        ) { backStackEntry ->
            val standard = backStackEntry.arguments?.getString("standard") ?: ""
            // Fetch students for the selected standard when the screen is displayed
            LaunchedEffect(standard) {
                if(uiState.studentList[0].standard != standard) {
                    schoolViewModel.fetchStudentsByStandard(standard = standard)
                    schoolViewModel.fetchTeachers()
                }
            }

            val studentList = uiState.studentList.distinct().filter { it.standard == standard }.sortedBy { it.rollNumber }
            val subjectList = studentList.firstOrNull()?.subjectMarks?.sortedBy { it.id } ?: emptyList()
            val classTeacher = uiState.teacherList.distinct().find { it.standard == standard }?.name ?: "Unavailable"

            ClassDescScreen(
                standard = standard,
                classTeacher = classTeacher,
                isStudentListEmpty = studentList.isEmpty(),
                subjectList = subjectList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onAttendanceClick = { std ->
                    Log.d("Navigation", "Navigating to Attendance Screen with grade level: $std")
                    navController.navigate("${Screen.Attendance.route}/$std")
                },
                onStudentsClick = { std ->
                    navController.navigate("${Screen.Students.route}/${std}")
                },
                modifier = modifier
            )
        }

        composable(
            route = "${Screen.Students.route}/{standard}",
            arguments = listOf(navArgument("standard") { type = NavType.StringType }),
        ) { backStackEntry ->
            val standard = backStackEntry.arguments?.getString("standard") ?: ""

            // Fetch students for the selected standard when navigating to Attendance screen
            LaunchedEffect(standard) {
                if(uiState.studentList[0].standard != standard) {
                    schoolViewModel.fetchStudentsByStandard(standard)
                }
            }

            val studentList = uiState.studentList.distinct().filter { it.standard == standard }.sortedBy { it.rollNumber }

            StudentsScreen(
                studentList = studentList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onStudentClick = { id ->
                    navController.navigate("${Screen.StudentProfile.route}/${id}")
                },
                onFabClick = {
                    navController.navigate(Screen.AddStudent.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = Screen.AddStudent.route
        ) {
            AddStudentScreen(
                onNavIconClick = { navController.navigateUp() },
                modifier = modifier,
//                onSubmitClick = { id, name, imageUrl, description, isClassTeacher, standard ->
//                    validateTeacherDetailsInput(id, name, imageUrl, isClassTeacher, standard, description, uiState)
//                    if(!uiState.anyMessage) {
////
////                    } else {
//                        schoolViewModel.saveTeacher(
//                            FirebaseTeacher(
//                                id = id,
//                                name = name,
//                                imageUrl = imageUrl,
//                                description = description,
//                                isClassTeacher = isClassTeacher,
//                                standard = standard
//                            )
//                        )
//                        navController.navigateUp()
//                        Toast.makeText(context, "Teacher added successfully!", Toast.LENGTH_SHORT).show()
//                    }
//                }
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
            val standard = backStackEntry.arguments?.getString("standard") ?: ""

            // Fetch students for the selected standard when navigating to Attendance screen
            LaunchedEffect(standard) {
                if(uiState.studentList[0].standard != standard) {
                    schoolViewModel.fetchStudentsByStandard(standard)
                    schoolViewModel.fetchTeachers()
                }
            }

            val studentList = uiState.studentList.distinct().filter { it.standard == standard }.sortedBy { it.rollNumber }

            AttendanceScreen(
                studentList = studentList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onSubmitClick = { attendanceMap ->
                    schoolViewModel.updateAttendance(standard, attendanceMap)
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
            LaunchedEffect(Unit) {
                schoolViewModel.fetchTeachers()
            }

            TeachersScreen(
                teacherList = uiState.teacherList,
                onNavIconClick = {
                    navController.navigateUp()
                },
                onCardClick = { id ->
                    navController.navigate("${Screen.TeacherProfile.route}/${id}")
                },
                onFabClick = {
                    navController.navigate(Screen.AddTeacher.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = Screen.AddTeacher.route
        ) {
            AddTeacherScreen(
                onNavIconClick = { navController.navigateUp() },
                modifier = modifier,
                onSubmitClick = { id, name, imageUrl, description, isClassTeacher, standard ->
                    validateTeacherDetailsInput(id, name, imageUrl, isClassTeacher, standard, description, uiState)
                    if(!uiState.anyMessage) {
//
//                    } else {
                        schoolViewModel.saveTeacher(
                            FirebaseTeacher(
                                id = id,
                                name = name,
                                imageUrl = imageUrl,
                                description = description,
                                isClassTeacher = isClassTeacher,
                                standard = standard
                            )
                        )
                        navController.navigateUp()
                        Toast.makeText(context, "Teacher added successfully!", Toast.LENGTH_SHORT).show()
                    }
                }
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
            LaunchedEffect(Unit) {
                schoolViewModel.fetchGalleryItems()
            }

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
}