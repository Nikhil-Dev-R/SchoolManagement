package com.rudraksha.school.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rudraksha.school.models.room.RoomGallery
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.ui.components.SchoolFab
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.utils.GalleryItemCard
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun StudentsScreen(
    studentList: List<RoomStudent>,
    onStudentClick: (String) -> Unit,
    onNavIconClick: () -> Unit,
    onFabClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            SchoolTopBar(
                title = "Students",
                navigationIcon = Icons.AutoMirrored.Outlined.ArrowBack,
                onNavIconClick = onNavIconClick,
            )
        },
        floatingActionButton = {
            SchoolFab(
                onClick = onFabClick
            ) {
                SchoolIcon(
                    imageVector = Icons.Outlined.Add,
                    modifier = Modifier.size(32.dp),
                )
            }
        }
    ) { innerPadding ->
        AnimatedVisibility(
            visible = true,
            enter = slideInHorizontally(
                animationSpec = tween(durationMillis = 1000),
                initialOffsetX = { 1000 }
            ),
            exit = slideOutHorizontally(
                animationSpec = tween(durationMillis = 1000),
                targetOffsetX = { -1000 }
            ),
        ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier
                        .padding(innerPadding)
                        .padding(horizontal = 8.dp),
                    content = {
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .padding(top = 8.dp)
                                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                                    .padding(8.dp),
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
                        items(studentList.size) { index ->
                            StudentItem(
                                student = studentList[index],
                                modifier = modifier,
                                onStudentClick = onStudentClick,
                            )
                        }
                    }
                )
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
            .height(40.dp)
            .padding(top = 4.dp)
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .padding(8.dp)
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
fun StudentScreenPreview() {
    StudentsScreen(
        listOf(),
        {},
        {},
    )
}
