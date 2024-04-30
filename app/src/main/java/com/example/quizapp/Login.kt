import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.FirebaseManager
import com.example.quizapp.ui.theme.QuizAppTheme

class Login : ComponentActivity() {
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
                    LoginPage(navController = navController, firebaseManager = FirebaseManager())
                }
            }
        }
    }
}

@Composable
fun LoginPage(navController: NavController, firebaseManager: FirebaseManager) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val customButtonColors = ButtonDefaults.buttonColors(
        Color(0xFF6A5AE0)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF6A5AE0)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                contentColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Login",
                    color = Color.Black,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                Button(
                    onClick = {
                        // Perform login using FirebaseManager
                        firebaseManager.signIn(
                            email = email,
                            password = password,
                            onSuccess = {
                                // Navigate to MainScreen upon successful login
                                navController.navigate("mainScreen") {
                                    // Pop back stack to prevent back navigation to login screen
                                    popUpTo("login") { inclusive = true }
                                }
                            },
                            onError = { errorMessage ->
                                // Handle login error
                                // Show error message to the user
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = customButtonColors
                ) {
                    Text(
                        text = "Login",
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Text for sign up option
                Text(
                    text = "Don't have an account? Sign Up",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .clickable {
                            // Navigate to the sign-up screen when clicked
                            navController.navigate("signup")
                        }
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginPage() {
    QuizAppTheme {
        val navController = rememberNavController()
        LoginPage(navController = navController, firebaseManager = FirebaseManager())
    }
}
