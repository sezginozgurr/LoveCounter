package com.example.lovecounter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lovecounter.presentation.navigation.BottomNavItem
import com.example.lovecounter.presentation.navigation.NavigationGraph
import com.example.lovecounter.presentation.navigation.Screen
import com.example.lovecounter.presentation.theme.MyappTheme
import com.example.lovecounter.presentation.components.CustomBottomNavigation
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.graphics.Color

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFFF5F5F5),
                    bottomBar = {
                        when (currentRoute) {
                            Screen.Home.route,
                            Screen.Memories.route,
                            Screen.Profile.route,
                            Screen.Settings.route -> {
                                CustomBottomNavigation(
                                    currentRoute = currentRoute,
                                    onNavigate = { route ->
                                        navController.navigate(route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    onFabClick = {
                                        // FAB tıklama işlemi
                                    }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    NavigationGraph(
                        navController = navController,
                        startDestination = Screen.Splash.route,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}