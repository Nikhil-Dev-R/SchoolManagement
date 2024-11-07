package com.rudraksha.school.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rudraksha.school.models.room.RoomGallery
import com.rudraksha.school.ui.components.SchoolCard
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText

@Composable
fun GalleryItemCard(
    item: RoomGallery,
    onItemClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(
            onClick = onItemClick
        )
    ) {
        SchoolCard(
            onCardClick = onItemClick,
            content = {
                SchoolImage(
                    imageUrl = item.imageUrl,
                    modifier = Modifier,
                )
            },
        )
        SchoolText(
            text = item.name,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview
@Composable
fun GalleryItemCardPreview() {
    GalleryItemCard(
        item = RoomGallery(
            id = "1",
            name = "Sample Image",
            imageUrl = "https://th.bing.com/th/id/OIP.5-ff1u_LA68PO1E7kroTAwHaNK?rs=1&pid=ImgDetMain",
            description = "This is a sample image"
        ),
        onItemClick = {}
    )
}