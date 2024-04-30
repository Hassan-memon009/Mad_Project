package com.example.quizapp

import LoginPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.QuizAppTheme

class MainActivity : ComponentActivity() {
    private val firebaseManager = FirebaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreenContent(navController) }
                    composable("login") { LoginPage(navController, firebaseManager) }
                    composable("signup") { SignupPage(navController, firebaseManager) }
                    composable("mainScreen") { MainScreenContent(navController) }
                    composable("singleQuiz") { SingleQuizContent(navController, firebaseManager, QuizViewModel()) }
                    composable("multiQuiz") { MultiQuizContent(navController) }
                    composable("singleScore") { SingleScoreScreen(navController, QuizViewModel()) }
                }
            }
        }
    }
}