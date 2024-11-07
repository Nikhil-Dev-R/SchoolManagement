package com.rudraksha.school

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.rudraksha.school.navigation.AppNavHost
import com.rudraksha.school.ui.screens.Screen
import com.rudraksha.school.ui.theme.SchoolTheme
import com.rudraksha.school.viewmodel.SchoolViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolTheme {
                SchoolApp(this)
            }
        }
    }
}

@Composable
fun SchoolApp(context: Context) {
    val navController = rememberNavController()
    val schoolViewModel = SchoolViewModel.getInstance(context)

    AppNavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        context = context,
        schoolViewModel = schoolViewModel,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SchoolTheme {
        SchoolApp(LocalContext.current)
    }
}

