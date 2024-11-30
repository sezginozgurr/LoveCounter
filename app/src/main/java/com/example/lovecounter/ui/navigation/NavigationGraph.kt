package com.example.lovecounter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lovecounter.ui.home.OnboardingScreen
import com.example.lovecounter.ui.navigation.Screen.homeRoute
import com.example.lovecounter.ui.navigation.Screen.onboardingRoute

object Screen {
    const val onboardingRoute = "OnboardingScreen"
    const val homeRoute = "HomeScreen"
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = onboardingRoute
    ) {

        composable(route = onboardingRoute) {
            OnboardingScreen(
                onFinishClicked = {
                    navController.navigate(homeRoute) {
                        popUpTo(onboardingRoute) {
                            inclusive = true
                        }
                    }
                },
                onSkipClicked = {
                    navController.navigate(homeRoute) {
                        popUpTo(onboardingRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = homeRoute) {
            OnboardingScreen({}){}
        }
    }
}