package com.example.javaapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ui.navigation.BottomNavigationBar
import ui.navigation.NavHostContainer
import ui.theme.JavaApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JavaApplicationTheme {

                val navController = rememberNavController()

                Surface(color = Color.White) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }, content = { padding ->
                            NavHostContainer(navController = navController, padding = padding)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JavaApplicationTheme {
        Greeting("Android")
    }
}