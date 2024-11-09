package com.rudraksha.school.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.rudraksha.school.R

@Composable
fun SchoolIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    size: Dp = 32.dp,
    tint: Color = MaterialTheme.colorScheme.primary,
    contentDescription: String? = "School Icon", // Default description for accessibility
) {
    Icon(
        imageVector = imageVector, // Replace with your icon resource
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size)
    )
}

@Preview(showBackground = true)
@Composable
fun SchoolIconPreview() {
    SchoolIcon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
        modifier = Modifier.size(24.dp),
        tint = Color.Blue,
    ) // Example usage with click functionality
}
