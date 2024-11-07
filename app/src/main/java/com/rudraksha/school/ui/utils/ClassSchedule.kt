package com.rudraksha.school.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun ClassSchedule(
    navController: NavController = NavController(LocalContext.current),
) {

}

@Preview
@Composable
fun ClassScheduleScreenPreview() {
    ClassSchedule()
}