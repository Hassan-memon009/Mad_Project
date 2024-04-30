package com.example.quizapp

data class QuizQuestion(
    val question_number: Int = 0,
    val question: String = "",
    val options: List<String> = emptyList(),
    val correct_answer: String = ""
) {
    // Empty constructor required by Firebase for deserialization
    constructor() : this(0, "", emptyList(), "")
}

