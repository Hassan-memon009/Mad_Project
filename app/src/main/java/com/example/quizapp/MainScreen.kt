package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.QuizAppTheme

class MainScreen(navController: NavHostController) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                val navController = rememberNavController()
                MainScreenContent(navController = navController)
            }
        }
    }
}

@Composable
fun MainScreenContent(navController: NavController) {
    val customButtonColors = ButtonDefaults.buttonColors(
        Color(0xFF6A5AE0),
    )

    val topImage: Painter = painterResource(id = R.drawable.logo)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6A5AE0))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            Image(
                painter = topImage,
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .width(500.dp)
                    .height(250.dp)
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(32.dp)
            )
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Select Your Quiz Type",
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight(700),
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Navigate to SingleQuiz when "Single Player" button is clicked
                        Button(
                            onClick = {
                                navController.navigate("singleQuiz") {
                                    // Define navigation actions if needed
                                }
                            },
                            modifier = Modifier
                                .width(120.dp)
                                .height(111.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = customButtonColors
                        ) {
                            Text(text = "Single Player")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Placeholder button for "Multi Player"
                        Button(
                            onClick = {
                                navController.navigate("multiQuiz") {
                                // Define navigation actions if needed
                            }},
                            modifier = Modifier
                                .width(120.dp)
                                .height(111.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = customButtonColors
                        ) {
                            Text(text = "Multi Player")
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    QuizAppTheme {
        val navController = rememberNavController()
        MainScreenContent(navController = navController)
    }
}
