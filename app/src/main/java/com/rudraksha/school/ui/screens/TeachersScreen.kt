package com.rudraksha.school.ui.screens

import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.school.models.room.RoomTeacher
import com.rudraksha.school.models.standardList
import com.rudraksha.school.ui.components.SchoolCard
import com.rudraksha.school.ui.components.SchoolFab
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun TeachersScreen(
    teacherList: List<RoomTeacher>,
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit = {},
    onCardClick: (String) -> Unit = {},
    onFabClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SchoolTopBar(
                title = "Teachers",
                modifier = Modifier,
                onNavIconClick = onNavIconClick
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(teacherList.size) { index ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SchoolCard(
                        content = {
                            SchoolImage(
                                imageUrl = teacherList[index].imageUrl,
                                contentScale = ContentScale.Crop
                            )
                        },
                        onCardClick = {
                            Log.d("Teacher Id", "Send ${teacherList[index].id}")
                            onCardClick(teacherList[index].id)
                        },
                        width = 150.dp,
                        height = 150.dp,
                    )
                    SchoolText(
                        text = teacherList[index].name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(4.dp),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Preview
@Composable
fun TeachersScreenPreview() {
    val teacherList = listOf(
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
        RoomTeacher(
            id = "12",
            name = "Abc",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "b",
            isClassTeacher = false,
            standard = ""
        ),
    )

    TeachersScreen(
        teacherList = teacherList,
    )
}

