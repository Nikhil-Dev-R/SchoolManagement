package com.rudraksha.school.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rudraksha.school.models.room.RoomGallery
import com.rudraksha.school.ui.utils.GalleryItemCard
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun GalleryScreen(
    galleryItems: List<RoomGallery>,
    onItemClick: (String) -> Unit,
    onNavIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Add navigation bar using Scaffold
    Scaffold(
        topBar = {
            SchoolTopBar(
                title = "Gallery",
                navigationIcon = Icons.AutoMirrored.Outlined.ArrowBack,
                onNavIconClick = onNavIconClick,
            )
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
            Surface(onClick = {}) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier.padding(innerPadding),
                    content = {
                        items(galleryItems.size) { index ->
                            GalleryItemCard(
                                item = galleryItems[index],
                                onItemClick = { onItemClick(galleryItems[index].id) }
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun GalleryScreenPreview() {
    GalleryScreen(
        galleryItems = listOf(
            RoomGallery(
                id = "01",
                name = "Festival 1",
                imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                description = "Desc F1",
            ),
            RoomGallery(
                id = "F2",
                name = "Festival 2",
                imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                description = "Desc F2",
            ),
            RoomGallery(
                id = "F3",
                name = "Festival 3",
                imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                description = "Desc F3",
            ),
            RoomGallery(
                id = "F4",
                name = "Festival 4",
                imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                description = "Desc F4",
            ),
            RoomGallery(
                id = "F5",
                name = "Festival 5",
                imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                description = "Desc F5",
            ),
            RoomGallery(
                id = "F6",
                name = "Festival 6",
                imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                description = "Desc F6",
            ),
            RoomGallery(
                id = "F7",
                name = "Festival 7",
                imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                description = "Desc F7",
            ),
            RoomGallery(
                id = "F8",
                name = "Festival 8",
                imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
                description = "Desc F8",
            ),
        ),
        onItemClick = {},
        modifier = Modifier,
        onNavIconClick = {}
    )
}
