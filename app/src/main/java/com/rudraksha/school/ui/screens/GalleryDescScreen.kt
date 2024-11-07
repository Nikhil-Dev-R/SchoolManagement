package com.rudraksha.school.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.school.models.room.RoomGallery
import com.rudraksha.school.ui.components.SchoolCard
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.theme.circleShapeMedium
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun GalleryDescScreen(
    galleryItem: RoomGallery,
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            SchoolTopBar(
                onNavIconClick = onNavIconClick,
                title = galleryItem.name
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(innerPadding).padding(16.dp),
        ) {
            SchoolCard(
                content = {
                    SchoolImage(
                        imageUrl = galleryItem.imageUrl,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = circleShapeMedium,
            )
            SchoolText(
                text = galleryItem.name,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
            SchoolText(
                text = galleryItem.description,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
    }
}

@Preview
@Composable
fun GalleryDescScreenPreview() {
    GalleryDescScreen(
        galleryItem = RoomGallery(
            id = "132",
            name = "Festival 1",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "The festival was celebrated on 12 may 2021"
        ),
    )
}
