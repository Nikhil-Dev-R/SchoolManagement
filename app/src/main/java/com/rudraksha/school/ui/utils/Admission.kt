package com.rudraksha.school.ui.utils

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.rudraksha.school.ui.components.SchoolText
import kotlinx.coroutines.delay

@Composable
fun Admission(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Determine the screen width for a consistent animation distance across devices
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val maxScrollLimit = with(LocalDensity.current) { screenWidth.toPx() } * 0.2f // 50% of screen width

    // Animation configurations
    val animationDuration = 1200
    var scrollTarget by remember { mutableFloatStateOf(0f) }

    // Animate the offset to smoothly move between 0 and maxScrollLimit
    val offsetX = animateFloatAsState(
        targetValue = scrollTarget,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    // Toggle the scroll target every cycle
    LaunchedEffect(Unit) {
        while (true) {
            scrollTarget = if (scrollTarget == 0f) maxScrollLimit else -scrollTarget
//            withFrameNanos { }
            delay(animationDuration.toLong())
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        SchoolText(
            text = "Admission Open",
            modifier = Modifier.offset { IntOffset(x = offsetX.value.toInt(), y = 0) },
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun AdmissionPreview() {
    Admission(
        onClick = {}
    )
}