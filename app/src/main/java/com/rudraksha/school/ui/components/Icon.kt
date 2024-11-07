package com.rudraksha.school.ui.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import com.rudraksha.school.R

@Composable
fun SchoolIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary, // Tint color, defaults to no tint
    contentDescription: String? = "School Icon", // Default description for accessibility
    onClick: (() -> Unit)? = null // Lambda for click functionality
) {
    Icon(
        imageVector = imageVector, // Replace with your icon resource
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .let {
                if (onClick != null)
                    it.clickable(onClick = onClick)
                else it
            } // Add clickable modifier if onClick is provided
    )
}

@Preview(showBackground = true)
@Composable
fun SchoolIconPreview() {
    SchoolIcon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
        modifier = Modifier.size(24.dp),
        tint = Color.Blue,
        onClick = { /* Handle click action */ }
    ) // Example usage with click functionality
}
