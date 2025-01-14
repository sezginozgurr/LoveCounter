package com.example.lovecounter.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lovecounter.presentation.onboarding.OnboardingScreen
import com.example.lovecounter.presentation.home.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding
    ) {

        composable<Screen.Onboarding> {
            OnboardingScreen(
                onFinishClicked = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Onboarding) {
                            inclusive = true
                        }
                    }
                },
                onSkipClicked = {
                    navController.navigate(Screen.Home) {
                        popUpTo(Screen.Onboarding) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Screen.Login> {
            HomeScreen()
        }
    }
}