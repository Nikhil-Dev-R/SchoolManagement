package com.rudraksha.school.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.rudraksha.school.database.dao.GalleryDao
import com.rudraksha.school.database.dao.TeacherDao
import com.rudraksha.school.database.SchoolDatabase
import com.rudraksha.school.database.dao.AppUserDao
import com.rudraksha.school.database.dao.StudentDao
import com.rudraksha.school.database.dao.SubjectDao
import com.rudraksha.school.models.firebase.FirebaseGallery
import com.rudraksha.school.models.firebase.FirebaseStudent
import com.rudraksha.school.models.firebase.FirebaseTeacher
import com.rudraksha.school.models.firebase.toRoomAppUser
import com.rudraksha.school.models.firebase.toRoomGallery
import com.rudraksha.school.models.firebase.toRoomStudent
import com.rudraksha.school.models.firebase.toRoomTeacher
import com.rudraksha.school.models.room.RoomAppUser
import com.rudraksha.school.models.room.RoomGallery
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomTeacher
import com.rudraksha.school.models.room.toFirebaseStudent
import com.rudraksha.school.repo.FirebaseAuthRepository
import com.rudraksha.school.repo.FirebaseRepository
import com.rudraksha.school.repo.LocalDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class SchoolViewModel(
    private val firebaseAuthRepo: FirebaseAuthRepository,
    private val firebaseRepo: FirebaseRepository,
    database: SchoolDatabase,
) : ViewModel() {

    private val studentDao: StudentDao = database.studentDao()
    val subjectDao: SubjectDao = database.subjectDao()
    private val teacherDao: TeacherDao = database.teacherDao()
    private val galleryDao: GalleryDao = database.galleryDao()

    private val appUserDao: AppUserDao = database.appUserDao()

    private val localDataRepository = LocalDataRepository()

    fun getQuote(): String {
        return try {
            localDataRepository.quotes.random()
        } catch (e: IndexOutOfBoundsException) {
            "Education is the foundation of a country's prosperity."
        }
    }

    private val _uiState = MutableStateFlow(SchoolUiState())
    val uiState: StateFlow<SchoolUiState> = _uiState

    // Fetch data from Firebase and save it in the Room database
    fun loadInitialData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setLoading(isLoading = true)
                localDataRepository.studentList.forEach { student ->
                    saveStudent(student)
                }
                localDataRepository.teacherList.forEach { teacher ->
                    saveTeacher(teacher)
                }
                localDataRepository.galleryList.forEach { galleryItem ->
                    saveGallery(galleryItem)
                }
                setLoading(isLoading = false)
            }
        }
    }

    fun fetchTeachers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setLoading(isLoading = true)
                teacherDao.getAllTeachers().forEach { teacher ->
                    loadTeacher(teacher)
                    Log.d("Fetched", "Loaded Teacher ${teacher.name} ${teacher.id}")
                }
//                reset()
                setLoading(isLoading = false)
            }
        }
    }
    fun fetchStudents() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setLoading(isLoading = true)
                studentDao.getAllStudents().forEach { student ->
                    loadStudent(student)
                    Log.d("Fetched", "Loaded Student ${student.name} ${student.standard}")
                }
                setLoading(isLoading = false)
//                reset()
            }
        }
    }
    fun fetchStudentsByStandard(standard: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setLoading(isLoading = true)
                // Clear the previous list of students before adding new ones for the selected standard
                _uiState.value = _uiState.value.copy(studentList = mutableListOf())
                studentDao.getAllStudentsByStandard(standard).forEach { student ->
                    loadStudent(student)
                    Log.d("Fetched", "Loaded StudentStandard ${student.name} ${student.standard}")
                }
                setLoading(isLoading = false)
//                reset()
            }
        }
    }
    fun fetchGalleryItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setLoading(isLoading = true)
                galleryDao.getAllGalleryItems().forEach { galleryItem ->
                    loadGalleryItem(galleryItem)
                    Log.d("Fetched", "Loaded Gallery ${galleryItem.name}")
                }
                setLoading(isLoading = false)
//                reset()
            }
        }
    }

    fun eraseAllData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setLoading(isLoading = true)
                studentDao.deleteAllStudents()
                teacherDao.deleteAllTeachers()
                galleryDao.deleteAllGalleryItems()
                appUserDao.deleteAllUsers()

                firebaseRepo.deleteAllStudents()
                firebaseRepo.deleteAllTeachers()
                firebaseRepo.deleteAllGalleryItems()

                _uiState.value = _uiState.value.copy(
                    studentList = mutableListOf(),
                    teacherList = mutableListOf(),
                    galleryList = mutableListOf()
                )
//                Log.d("Deletion", "Successful")
                setLoading(isLoading = false)
//                reset()
            }
        }
    }

    // Register a new student
    fun saveStudent(student: FirebaseStudent) {
        viewModelScope.launch {
            setLoading(isLoading = true)
            try {
                firebaseRepo.saveStudent(student)
                studentDao.insertStudent(student.toRoomStudent())
                loadStudent(student.toRoomStudent())
            } catch (e: Exception) {
                setError("Error saving student: ${e.message}")
            } finally {
//                Log.d("FLS", "Calling reset")
                setLoading(isLoading = false)
//                reset()
            }
        }
    }

    // Load students from Room
    private fun loadStudent(student: RoomStudent) {
        viewModelScope.launch {
            _uiState.value.studentList.add(student)
//            Log.d("Student", "Insertion successful")
            }
    }

    // Save teacher to Firebase and Room
    fun saveTeacher(teacher: FirebaseTeacher) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setLoading(isLoading = true)
                try {
                    firebaseRepo.saveTeacher(teacher)
                    teacherDao.insertTeacher(teacher.toRoomTeacher())
//                Log.d("Teacher", "inserted ${teacher.name}")
                    loadTeacher(teacher.toRoomTeacher())
                } catch (e: Exception) {
                    setError("Error saving teacher: ${e.message}")
                } finally {
//                    Log.d("FLS", "Calling reset")
                    setLoading(isLoading = false)
//                    reset()
                }
            }
        }
    }

    private fun loadTeacher(teacher: RoomTeacher) {
        viewModelScope.launch {
            _uiState.value.teacherList.add(teacher)
//                Log.d("Teacher", "${teacher.name} ${_uiState.value.teacherList.size}")
        }
    }

    // Save gallery item
    fun saveGallery(galleryItem: FirebaseGallery) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setLoading(isLoading = true)
                try {
                    firebaseRepo.saveGalleryItem(galleryItem)
                    galleryDao.insertGalleryItem(galleryItem.toRoomGallery())
                    loadGalleryItem(galleryItem.toRoomGallery())
                } catch (e: Exception) {
                    setError("Error saving gallery item: ${e.message}")
                } finally {
//                    Log.d("FLS", "Calling reset")
                    setLoading(isLoading = false)
//                    reset()
                }
            }
        }
    }

    private fun loadGalleryItem(gallery: RoomGallery) {
        viewModelScope.launch {
            _uiState.value.galleryList.add(gallery)
//                Log.d("Gallery", "${gallery.name} ${_uiState.value.galleryList.size}")
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthRepo.getCurrentUser()
    }

    fun isLoggedIn(): Boolean {
        firebaseAuthRepo.getCurrentUser()?.displayName?.let { Log.d("Current user name", it) }
        firebaseAuthRepo.getCurrentUser()?.email?.let { Log.d("Current user email", it) }
        firebaseAuthRepo.getCurrentUser()?.uid?.let { Log.d("Current user uid", it) }
        return firebaseAuthRepo.isLoggedIn()
    }

    // Handle login, checking both Firebase and Room
    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            setLoading(isLoading = true)
            firebaseAuthRepo.login(email, password) { firebaseSuccess ->
                if (firebaseSuccess) {
                    handleFirebaseLoginSuccess(password, onResult)
                }
            }
            setLoading(isLoading = false)
        }
    }

    private fun handleFirebaseLoginSuccess(password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    firebaseAuthRepo.getCurrentUser()?.let { firebaseUser ->
                        val roomAppUser = firebaseUser.toRoomAppUser(password)
                        appUserDao.insertUser(roomAppUser)
                        setLoading(isLoading = true)
                        loadLoggedInUser()
                        onResult(true)
                    } ?: onResult(false)
                } catch (e: Exception) {
                    e.message?.let { setError(it) }
                    onResult(false)
                } finally {
                    setLoading(isLoading = false)
//                Log.d("FLS", "Calling reset in firebase login")
                }
            }
        }
    }

    // Handle registration
    fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            setLoading(isLoading = true)
            firebaseAuthRepo.register(email, password) { firebaseSuccess ->
                if (firebaseSuccess) {
                    handleFirebaseLoginSuccess(password, onResult)
                } else {
                    setError("Registration failed.")
                    onResult(false)
                }
            }
            setLoading(isLoading = false)
        }
    }

    // Load logged in user
    private fun loadLoggedInUser() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                loggedInUser = appUserDao.getLoggedInUser() // Assuming this returns the logged-in user
            )
        }
    }

    fun signOut() {
        viewModelScope.launch {
            firebaseAuthRepo.signOut()
            _uiState.value = _uiState.value.copy(
                isLoggedIn = false,
                loggedInUser = null
            )
        }
    }

    // Reset function with better state handling
    fun reset() {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            anyMessage = false,
            toastMessage = "",
        )
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }

    private fun setError(errorMsg: String) {
        _uiState.value = _uiState.value.copy(anyMessage = true, toastMessage = errorMsg)
        // Log the error if needed for debugging: Log.e("SchoolViewModel", errorMsg)
    }

    fun updateAttendance(standard: String, attendanceMap: Map<String, Boolean>) {
        viewModelScope.launch {
            _uiState.value.studentList.forEach { student ->
                saveStudent(
                    student.copy(
                        presentDays = attendanceMap[student.id]?.let { if (it) student.presentDays + 1 else student.presentDays } ?: student.presentDays
                    ).toFirebaseStudent()
                )
            }
            _uiState.value = _uiState.value.copy(
                anyMessage = true,
                toastMessage = "Attendance updated"
            )
        }
    }

    // Helper function to check network availability
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }

    companion object {
        @Volatile
        private var INSTANCE: SchoolViewModel? = null

        fun getInstance(context: Context): SchoolViewModel {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SchoolViewModel(
                    FirebaseAuthRepository(),
                    FirebaseRepository(),
                    SchoolDatabase.getDatabase(context)
                ).also { INSTANCE = it }
            }
        }
    }
}

data class SchoolUiState(
    val isLoading: Boolean = false,
    var anyMessage: Boolean = false,
    var toastMessage: String = "",
    val isLoggedIn: Boolean = false,
    val loggedInUser: RoomAppUser? = null,
    var studentList: MutableList<RoomStudent> = mutableListOf(),
    val teacherList: MutableList<RoomTeacher> = mutableListOf(),
    val galleryList: MutableList<RoomGallery> = mutableListOf(),
    // For adding and editing
    val currentFirebaseTeacher: FirebaseTeacher = FirebaseTeacher(),
    val currentFirebaseStudent: FirebaseStudent = FirebaseStudent(),
)
