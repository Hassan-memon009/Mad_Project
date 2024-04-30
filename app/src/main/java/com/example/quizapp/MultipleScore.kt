package com.example.quizapp

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.QuizAppTheme

class MultipleScore : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                MultipleScoreScreen()
            }
        }
    }
}

@Composable
fun MultipleScoreScreen() {
    val userScores = listOf(
        UserScore("John", 8),
        UserScore("Alice", 7),
        UserScore("Bob", 6),
        // Add more user scores as needed
    )

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
            // Heading for multiplayer scores
            Text(
                text = "Multiplayer Scores", fontSize = 50.sp,
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(bottom = 16.dp, start = 10.dp)
            )

            // Display user scores using separate cards
            userScores.forEachIndexed { index, userScore ->
                UserScoreCard(userScore = userScore, rank = index + 1)
            }
        }
    }
}

@Composable
fun UserScoreCard(userScore: UserScore, rank: Int) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon for ranking
            val icon = when (rank) {
                1 -> Icons.Default.Star
                2 -> Icons.Default.AddCircle
                3 -> Icons.Default.ArrowForward
                else -> null
            }
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "Rank $rank",
                    tint = Color.Yellow,
                    modifier = Modifier.size(24.dp)
                )
            }

            // User name
            Text(
                text = userScore.name,
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )

            // Score
            Text(
                text = "${userScore.score}/10",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

data class UserScore(val name: String, val score: Int)

@Preview(showBackground = true)
@Composable
fun MultipleScorePreview() {
    QuizAppTheme {
        MultipleScoreScreen()
    }
}
