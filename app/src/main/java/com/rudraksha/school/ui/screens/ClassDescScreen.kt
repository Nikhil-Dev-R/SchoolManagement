package com.rudraksha.school.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    subjectList: List<RoomSubject>,
    modifier: Modifier = Modifier,
    isStudentListEmpty: Boolean = false,
    onNavIconClick: () -> Unit = {},
    onAttendanceClick: (String) -> Unit = {},
    onStudentsClick: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            SchoolTopBar(
                onNavIconClick = onNavIconClick,
                title = "$standard Class"
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(innerPadding)
        ) {
            SchoolText(
                text = "Class Description",
                style = MaterialTheme.typography.headlineLarge
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
                    style = MaterialTheme.typography.titleLarge,
                    color = if (classTeacher == "Unavailable") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Subjects
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SchoolText(
                    text = "Subjects:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .weight(1f)
                )
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (subjectList.isEmpty()) {
                        SchoolText(
                            text = "Unavailable",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    } else {
                        subjectList.chunked(2).forEach { rowSubjects -> // Divide into rows of 2
                            Row( // Use Row for horizontal alignment
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                rowSubjects.forEach { subject ->
                                    SchoolText(
                                        text = subject.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.weight(1f) // Equal width for subjects
                                    )
                                }
                                // Add a spacer if only one subject in the row
                                if (rowSubjects.size == 1) {
                                    Spacer(modifier =Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (isStudentListEmpty) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    SchoolText(
                        text = "No students found!",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SchoolButton(
                        content = {
                            SchoolText(
                                text = "Attendance",
                                style = MaterialTheme.typography.headlineMedium,
                            )
                        },
                        onClick = { onAttendanceClick(standard) },
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    SchoolButton(
                        content = {
                            SchoolText(
                                text = "Students",
                                style = MaterialTheme.typography.headlineMedium,
                            )
                        },
                        onClick = { onStudentsClick(standard) },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
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
        listOf()
    )
}