package com.rudraksha.school.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomSubject
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun ClassDescScreen(
    standard: String,
    classTeacher: String,
    studentList: List<RoomStudent>,
    subjectList: List<RoomSubject>,
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit = {},
    onAttendanceClick: (String) -> Unit = {},
    onStudentClick: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            SchoolTopBar(
                onNavIconClick = onNavIconClick,
                title = "$standard Class"
            )
        }
    ) { innerPadding ->
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            items(1) {
                SchoolText(
                    text = "Class Description",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SchoolText(
                        text = "Class Teacher",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    SchoolText(
                        text = classTeacher,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                // Subjects
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SchoolText(text = "Subjects:",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Column {
                        subjectList.forEach { subject ->
                            SchoolText(
                                text = subject.name,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                SchoolButton(
                    content = {
                        SchoolText(
                            text = "Attendance",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    },
                    onClick = { onAttendanceClick(standard) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                if(studentList.isEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        SchoolText(
                            text = "No students found!",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                } else {
                    SchoolText(
                        text = "Students",
                        style = MaterialTheme.typography.headlineMedium,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .fillMaxWidth(),
                    ) {
                        SchoolText(
                            text = "Adm. ID",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )

                        SchoolText(
                            text = "Roll No",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )

                        SchoolText(
                            text = "Name",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

            items(studentList.size) { index ->
                StudentItem(
                    student = studentList[index],
                    modifier = modifier,
                    onStudentClick = onStudentClick,
                )
            }
        }
    }
}

@Composable
fun StudentItem(
    student: RoomStudent,
    modifier: Modifier = Modifier,
    onStudentClick: (String) -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onStudentClick(student.id) }
            ),
    ) {
        SchoolText(
            text = student.id,
            style = MaterialTheme.typography.bodyLarge,
        )

        SchoolText(
            text = student.rollNumber,
            style = MaterialTheme.typography.bodyLarge,
        )

        SchoolText(
            text = student.name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun StudentItemPreview() {
    StudentItem(
        RoomStudent(
            id = "12",
            name = "SAD",
            rollNumber = "0012",
            standard = "10th",
            section = "A",
            presentDays = 25,
            subjectMarks = listOf(
                RoomSubject(
                    id = "S1",
                    name = "Subject1",
                    marks = 90
                ),
                RoomSubject(
                    id = "S2",
                    name = "Subject2",
                    marks = 80
                ),
                RoomSubject(
                    id = "S3",
                    name = "Subject3",
                    marks = 92
                ),
            )
        )
    )
}

@Preview
@Composable
fun ClassDescScreenPreview() {
    ClassDescScreen(
        "5th",
        "Elena",
        listOf(),
        listOf()
    )
}