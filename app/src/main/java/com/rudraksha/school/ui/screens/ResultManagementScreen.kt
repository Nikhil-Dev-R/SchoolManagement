package com.rudraksha.school.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomSubject
import com.rudraksha.school.ui.components.SchoolBox
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.theme.roundedCornerShapeMedium
import com.rudraksha.school.ui.utils.SchoolTopBar
import com.rudraksha.school.ui.utils.saveImage
import com.rudraksha.school.ui.utils.shareImage

@Composable
fun ResultManagementScreen(
    student: RoomStudent,
    context: Context,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val subjects = student.subjectMarks
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            SchoolTopBar(
                onNavIconClick = {
                    navController.navigateUp()
                },
                title = "Result Management",
            )
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 48.dp)
                    .zIndex(1f)
            ) {
                SchoolButton(
                    content = {
                        SchoolText(
                            text = "Share",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    },
                    shape = CutCornerShape(16.dp, 0.dp, 16.dp, 0.dp),
                    onClick = { shareImage(context) }
                )

                SchoolButton(
                    content = {
                        SchoolText(
                            text = "Save",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    },
                    onClick = { saveImage(context) }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .wrapContentHeight()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReportCard(
                studentName = student.name,
                studentId = student.id,
                gradeLevel = student.standard,
                section = student.section,
                schoolName = "Springfield High School",
                academicYear = "2022-2023",
                subjects = subjects,
                overallGrade = "A-",
                teacherComment = "John has shown excellent performance in all subjects. Keep up the good work!",
            )
        }
    }
}

@Composable
fun ReportCard(
    studentName: String,
    studentId: String,
    gradeLevel: String,
    section: String,
    schoolName: String,
    academicYear: String,
    subjects: List<RoomSubject>,
    overallGrade: String,
    teacherComment: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
            .border(width = 2.dp, color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SchoolText(
            text = schoolName,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = Modifier.height(4.dp))

        SchoolText(
            text = "Report Card",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                SchoolText(
                    text = "Name: $studentName",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary
                )
                SchoolText(
                    text = "ID: $studentId",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                SchoolText(
                    text = "Grade Level: $gradeLevel",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary
                )
                SchoolText(
                    text = "Section: $section",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SchoolText(
            text = "Academic Year: $academicYear",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        SchoolText(
            text = "Subjects and Marks",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        subjects.forEach { subject ->
            SubjectRow(subject)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        SchoolText(
            text = "Overall Grade: $overallGrade",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        SchoolText(
            text = "Teacher's Comment",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary,
        )

        Spacer(modifier = Modifier.height(4.dp))

        SchoolBox(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    ),
                    shape = roundedCornerShapeMedium
                )
                .clip(shape = roundedCornerShapeMedium),
        ) {
            SchoolText(
                text = teacherComment,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(4.dp),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        
        SignatureRow()

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SubjectRow(subject: RoomSubject) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = subject.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subject.marks.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SignatureRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SchoolText(
                text = "Teacher's Signature",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            SchoolImage(
                imageUrl = "https://example.com/teacher_signature.jpg",
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp),
                contentScale = ContentScale.FillBounds
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SchoolText(
                text = "Principal's Signature",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            SchoolImage(
                imageUrl = "https://example.com/teacher_signature.jpg",
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview
@Composable
fun PreviewCard() {
    val subjects = listOf(
        RoomSubject("M","Mathematics", 90),
        RoomSubject("S", "Science", 85),
        RoomSubject("Hi", "History", 97),
        RoomSubject("A", "Art", 89),
        RoomSubject("PE", "Physical Education", 87)
    )
    ResultManagementScreen(
        student = RoomStudent(
            "John Doe",
            "123456",
            "John The Don",
            "9th",
            "A",
            presentDays = 15,
            subjectMarks = subjects
        ),
        context = LocalContext.current,
        navController = NavController(LocalContext.current)
    )
}
@Preview
@Composable
fun PreviewReportCard() {
    val subjects = listOf(
        RoomSubject("M","Mathematics", 90),
        RoomSubject("S", "Science", 85),
        RoomSubject("Hi", "History", 97),
        RoomSubject("A", "Art", 89),
        RoomSubject("PE", "Physical Education", 87)
    )
    ReportCard(
        studentName = "John Doe",
        studentId = "123456",
        gradeLevel = "9th",
        section = "A",
        schoolName = "Springfield High School",
        academicYear = "2022-2023",
        subjects = subjects,
        overallGrade = "A-",
        teacherComment = "John has shown excellent performance in all subjects. Keep up the good work!",
    )
}