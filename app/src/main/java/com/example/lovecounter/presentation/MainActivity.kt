package com.example.lovecounter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.lovecounter.delegation.navigator.RememberNavController
import com.example.lovecounter.presentation.navigation.NavigationGraph
import com.example.lovecounter.presentation.theme.MyappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyappTheme {
                val navController = rememberNavController()
                RememberNavController(
                    navController = navController,
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}