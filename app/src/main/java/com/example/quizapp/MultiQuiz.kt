package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.QuizAppTheme

class MultiQuiz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val firebaseManager = FirebaseManager() // Create an instance of FirebaseManager
                    MultiQuizContent(navController = navController)
                }
            }
        }
    }
}

@Composable
fun MultiQuizContent(navController: NavController) {

    val customButtonColors = ButtonDefaults.buttonColors(
        Color(0xFF6A5AE0),
    )

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF6A5AE0),
            Color(0xFFFE7D0EC)
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBackground)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Multiplayer Quiz", fontFamily = FontFamily.Cursive,
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.padding(bottom = 16.dp, start = 16.dp)
                    )

                    // Card for displaying the quiz question
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(Color(0xFFFDAD8F3))
                    ) {
                        Text(
                            text = "What is the capital of France?",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    // Card for displaying answer options
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(Color.Transparent)

                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Answer Buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                AnswersButton("Option A", modifier = Modifier.weight(1f))
                                AnswersButton("Option B", modifier = Modifier.weight(1f))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                AnswersButton("Option C", modifier = Modifier.weight(1f))
                                AnswersButton("Option D", modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Next Button
                            Button(
                                onClick = { /* Handle next button click */ },
                                modifier = Modifier.fillMaxWidth(),
                                colors = customButtonColors,
                            ) {
                                Text(text = "Next")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AnswersButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* Handle answer button click */ },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(0.48f), // 0.48f to allow space between buttons
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF40389D),
            //backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun MultiQuizPreview() {
    val navController = rememberNavController()
    val firebaseManager = FirebaseManager() // Create an instance of FirebaseManager
    MultiQuizContent(navController = navController)
}