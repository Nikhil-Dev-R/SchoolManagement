package com.rudraksha.school.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomSubject
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun AttendanceScreen(
    studentList: List<RoomStudent>,
    onNavIconClick: () -> Unit,
    onSubmitClick: (Map<String, Boolean>) -> Unit,
    modifier: Modifier = Modifier
) {
    val attendanceMap = mutableMapOf<String, Boolean>()

    Scaffold(
        topBar = {
            SchoolTopBar(
                title = "Attendance",
                onNavIconClick = onNavIconClick
            )
        },
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 48.dp)
                    .zIndex(1f)
            ) {
                SchoolButton(
                    content = {
                        SchoolText(
                            text = "Submit",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    },
                    onClick = { onSubmitClick(attendanceMap) },
                )
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = modifier
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)
                    .wrapContentHeight(),
            ) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(top = 8.dp)
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                            .padding(8.dp)
                    ) {
                        SchoolText(
                            text = "Roll No",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        SchoolText(
                            text = "Student Name",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        SchoolText(
                            text = "Present",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                items(studentList.size) {
                    StudentAttendanceItem(
                        attendanceMap = attendanceMap,
                        id = studentList[it].id,
                        rollNo = studentList[it].rollNumber,
                        name = studentList[it].name,
                    )
                }
            }
        }
    }
}

@Composable
fun StudentAttendanceItem(
    attendanceMap: MutableMap<String, Boolean>,
    id: String,
    rollNo: String,
    name: String,
    modifier: Modifier = Modifier,
) {
    val isChecked = rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(key1 = isChecked.value) {
        attendanceMap[id] = isChecked.value
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(top = 4.dp)
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .padding(8.dp)
            .clickable { 
                isChecked.value = !isChecked.value
            },
    ) {
        SchoolText(
            text = rollNo,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        SchoolText(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
            },
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun StudentAttendanceItemPreview() {
    StudentAttendanceItem(
        attendanceMap = mutableMapOf(),
        id = "12",
        rollNo = "12",
        name = "Student Name",
    )
}

@Preview
@Composable
fun AttendanceScreenPreview() {
    AttendanceScreen(
        listOf(
            RoomStudent(
                id = "12",
                name = "ada",
                standard = "10th",
                rollNumber = "12",
                section = "B",
                presentDays = 20,
                subjectMarks = listOf(
                    RoomSubject(
                        id = "12",
                        name = "Sub1",
                        marks = 90
                    ),

                    )
            ),
            RoomStudent(
                id = "12",
                name = "ada",
                standard = "10th",
                rollNumber = "12",
                section = "B",
                presentDays = 20,
                subjectMarks = listOf(
                    RoomSubject(
                        id = "12",
                        name = "Sub1",
                        marks = 90
                    ),

                    )
            ),
            RoomStudent(
                id = "12",
                name = "ada",
                standard = "10th",
                rollNumber = "12",
                section = "B",
                presentDays = 20,
                subjectMarks = listOf(
                    RoomSubject(
                        id = "12",
                        name = "Sub1",
                        marks = 90
                    ),

                    )
            ),
            RoomStudent(
                id = "12",
                name = "ada",
                standard = "10th",
                rollNumber = "12",
                section = "B",
                presentDays = 20,
                subjectMarks = listOf(
                    RoomSubject(
                        id = "12",
                        name = "Sub1",
                        marks = 90
                    ),

                    )
            ),

            ),
        onNavIconClick = {},
        onSubmitClick = {},
    )
}