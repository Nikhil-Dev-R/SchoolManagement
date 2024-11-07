package com.rudraksha.school.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomSubject
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolCard
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.theme.roundedCorners
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun StudentProfileScreen(
    student: RoomStudent,
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit = {},
    onResultClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            SchoolTopBar(
                onNavIconClick = onNavIconClick,
                title = "Student Profile"
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = modifier.padding(innerPadding).padding(16.dp)
        ) {
            // Student Image
            SchoolCard(
                content = {
                    SchoolImage(
                        imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                        contentScale = ContentScale.Crop
                    )
                },
                shape = roundedCorners,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Student Details
            val details = listOf(
                "Admission ID" to student.id,
                "Name" to student.name,
                "Roll Number" to student.rollNumber,
                "Class" to student.standard,
                "Section" to student.section,
                "Present Days" to student.presentDays.toString(),
            )

            details.forEach { (label, value) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SchoolText(text = "$label:", fontWeight = FontWeight.Bold)
                    SchoolText(text = value)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Subjects
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SchoolText(text = "Subjects:", fontWeight = FontWeight.Bold)
                Column {
                    student.subjectMarks.forEach { subject ->
                        SchoolText(text = subject.name)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Report Card Button
            SchoolButton(
                content = {
                    SchoolText(
                        text = "Report Card",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = onResultClick,
            )
        }
    }
}

@Preview
@Composable
fun StudentProfileScreenPreview() {
    val student = RoomStudent(
        id = "G1125",
        name = "John Doe",
        rollNumber = "12345",
        standard = "10th",
        section = "A",
        presentDays = 25,
        subjectMarks = listOf(
            RoomSubject("M", "Mathematics", 90),
            RoomSubject("S", "Science", 85),
            RoomSubject("E", "English", 92)
        )
    )
    StudentProfileScreen(student = student)
}
