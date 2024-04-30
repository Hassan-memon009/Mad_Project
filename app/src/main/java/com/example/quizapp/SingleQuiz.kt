package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.QuizAppTheme

class SingleQuiz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                val navController = rememberNavController()
                val firebaseManager = FirebaseManager()
                val viewModel = remember { QuizViewModel() }

                SingleQuizContent(navController = navController, firebaseManager = firebaseManager, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun SingleQuizContent(navController: NavController, firebaseManager: FirebaseManager, viewModel: QuizViewModel) {
    var quizQuestions by remember { mutableStateOf<List<QuizQuestion>>(emptyList()) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var userScore by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(true) {
        firebaseManager.getQuizQuestions(
            onSuccess = { questions ->
                quizQuestions = questions
            },
            onError = { errorMessage ->
                // Handle error loading quiz questions
                Log.e("SingleQuizContent", "Error loading quiz questions: $errorMessage")
            }
        )
    }

    val currentQuestion = quizQuestions.getOrNull(currentQuestionIndex)

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (currentQuestion != null) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Card for displaying the question
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(Color(0xFFFDAD8F3)),
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Question ${currentQuestion.question_number}:",
                                style = MaterialTheme.typography.displaySmall,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = currentQuestion.question,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }
                    }

                    // Display answer options as buttons
                    currentQuestion.options.forEach { option ->
                        AnswerButton(
                            text = option,
                            onClick = {
                                selectedOption = option
                                if (option == currentQuestion.correct_answer) {
                                    userScore += 1
                                }
                            },
                            selected = selectedOption == option
                        )
                    }

                    // Display the "Next" button directly after an option is selected
                    selectedOption?.let {
                        NextButton(
                            onClick = {
                                selectedOption = null // Reset selected option
                                if (currentQuestionIndex < quizQuestions.size - 1) {
                                    currentQuestionIndex++
                                } else {
                                    // Quiz finished, navigate to score screen
                                    navController.navigate("singleScore") {
                                        launchSingleTop = true
                                        // Inside the navigation logic after completing the quiz
                                        viewModel.userScore.value = userScore // Update with the actual score
                                        viewModel.totalQuestions.value = quizQuestions.size // Update with the total number of questions

                                        popUpTo(navController.graph.startDestinationId)
                                    }
                                }
                            }
                        )
                    }
                }
            } else {
                Text(text = "Loading quiz questions...", style = MaterialTheme.typography.displayMedium)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnswerButton(text: String, onClick: () -> Unit, selected: Boolean) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) Color(0xFFFDAD8F3) else Color.Transparent
        ),
        onClick = onClick
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun NextButton(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(contentColor = Color.White)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF40389D),
                contentColor = Color.White
            )
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSingleQuizContent() {
    QuizAppTheme {
        val navController = rememberNavController()
        SingleQuizContent(navController = navController, firebaseManager = FirebaseManager(), viewModel = QuizViewModel())
    }
}