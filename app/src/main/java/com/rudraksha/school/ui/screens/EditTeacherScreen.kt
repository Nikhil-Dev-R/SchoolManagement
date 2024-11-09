package com.rudraksha.school.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.school.R
import com.rudraksha.school.models.room.RoomTeacher
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolCard
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.theme.roundedCorners
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun EditTeacherScreen(
    teacher: RoomTeacher,
    onNavIconClick: () -> Unit,
    onClassClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            SchoolTopBar(
                title = "Edit Profile",
                navigationIcon = Icons.AutoMirrored.Outlined.ArrowBack,
                onNavIconClick = onNavIconClick,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            SchoolCard(
                content = {
                    SchoolImage(
                        imageUrl = teacher.imageUrl,
                        contentScale = ContentScale.Crop
                    )
                },
                shape = roundedCorners,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = modifier.padding(16.dp)
            ) {
                SchoolText(
                    text = teacher.name,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )

                SchoolText(
                    text = teacher.description,
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (teacher.isClassTeacher) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        SchoolButton(
                            content = {
                                SchoolText(
                                    text = "Class Teacher ${teacher.standard}",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            onClick = { onClassClick(teacher.standard) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun EditTeacherScreenPreview() {
    EditTeacherScreen(
        teacher = RoomTeacher(
            id = "12",
            name = "John Doe",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = stringResource(id = R.string.school_desc_200),
            isClassTeacher = true,
            standard = "9th",
        ),
        onNavIconClick = {},
        onClassClick = {}
    )
}
