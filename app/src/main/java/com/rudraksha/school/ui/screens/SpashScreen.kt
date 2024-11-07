package com.rudraksha.school.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rudraksha.school.ui.components.SchoolBox

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        navigateTo()
    }

    SchoolBox(
        modifier = modifier,
    ) {
        Text(
            text = "Welcome to your School App",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
