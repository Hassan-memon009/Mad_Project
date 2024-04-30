package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.QuizAppTheme

class SingleScore: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                val navController = rememberNavController()
                val viewModel = remember { QuizViewModel() }
                SingleScoreScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun SingleScoreScreen(navController: NavController, viewModel: QuizViewModel) {
    val userScore = viewModel.userScore
    val totalQuestions = viewModel.totalQuestions

    // Display the score based on the viewModel's state
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF6A5AE0)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display user score and total questions
            Text(
                text = "Your Score",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.SansSerif
            )
            Text(
                text = "$userScore out of $totalQuestions",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.SansSerif
            )

            // Retake Quiz button
            Button(
                onClick = {
                    viewModel.userScore.value = 0 // Reset user score
                    navController.popBackStack() // Go back to the previous screen
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Retake Quiz")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleScorePreview() {
    QuizAppTheme {
        val navController = rememberNavController()
        val viewModel = QuizViewModel() // Create an instance of QuizViewModel
        SingleScoreScreen(navController = navController, viewModel = viewModel)
    }
}