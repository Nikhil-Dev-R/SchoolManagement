package com.rudraksha.school.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.school.ui.components.SchoolBox
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    quote: String,
    modifier: Modifier = Modifier,
    navigateTo: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        delay(1000)
        navigateTo()
    }

    SchoolBox(
        modifier = modifier
            .padding(16.dp),
    ) {
        Text(
            text = quote,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        "Believe in yourself, achieve greatness."
    )
}
