package com.example.lovecounter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.lovecounter.delegation.navigator.RememberNavController
import com.example.lovecounter.presentation.navigation.NavigationGraph
import com.example.lovecounter.presentation.theme.LCTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LCTheme {
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