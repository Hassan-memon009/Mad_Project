package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.QuizAppTheme
import kotlinx.coroutines.delay

class SplashScreen(navController: NavHostController) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                val navController = rememberNavController()
                SplashScreenContent(navController = navController)
            }
        }
    }
}

@Composable
fun SplashScreenContent(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF6A5AE0)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )

        LaunchedEffect(Unit) {
            delay(3000L)
            // Navigate to the login screen after the splash screen delay
            navController.navigate("login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    QuizAppTheme {
        val navController = rememberNavController()
        SplashScreenContent(navController = navController)
    }
}
