package com.example.quizapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    val userScore = mutableStateOf(0)
    val totalQuestions = mutableStateOf(0)
}