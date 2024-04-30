package com.example.quizapp

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlin.Unit;

class FirebaseManager {

    private val TAG = "FirebaseManager"
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val usersRef : DatabaseReference = database.getReference("users")

    fun signUp(email: String, password: String, username: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User signed up successfully
                val userId = auth.currentUser?.uid ?: ""
                val userData = User(userId, username, email, password)
                usersRef.child(userId).setValue(userData)
                onSuccess.invoke()
            } else {
                // Handle signup failure
                val errorMessage = task.exception?.message ?: "Signup failed"
                Log.e(TAG, "Signup Error: $errorMessage")
                onError.invoke(errorMessage)
            }
        }
    }

    fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login successful
                    onSuccess.invoke()
                } else {
                    // Login failed
                    val errorMessage = task.exception?.message ?: "Login failed"
                    Log.e(TAG, "Login Error: $errorMessage")
                    onError.invoke(errorMessage)
                }
            }
    }
    fun getQuizQuestions(
        onSuccess: (List<QuizQuestion>) -> Unit,
        onError: (String) -> Unit
    ) {
        val questionsRef = database.getReference("questions")

        questionsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val quizQuestions = mutableListOf<QuizQuestion>()

                for (questionSnapshot in snapshot.children) {
                    val question = questionSnapshot.getValue(QuizQuestion::class.java)
                    question?.let {
                        quizQuestions.add(it)
                    }
                }

                Log.d(TAG, "Quiz Questions Retrieved: $quizQuestions")

                onSuccess.invoke(quizQuestions)
            }

            override fun onCancelled(error: DatabaseError) {
                onError.invoke(error.message)
            }
        })
    }
}

data class User(val userId: String = "", val username: String = "", val email: String = "", val password: String = "")
