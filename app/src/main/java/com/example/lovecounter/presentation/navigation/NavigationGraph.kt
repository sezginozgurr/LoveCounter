package com.example.lovecounter.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lovecounter.presentation.home.HomeScreen
import com.example.lovecounter.presentation.memories.AddMemoryScreen
import com.example.lovecounter.presentation.memories.MemoriesScreen
import com.example.lovecounter.presentation.onboarding.OnboardingScreen
import com.example.lovecounter.presentation.onboarding.OnboardingViewModel
import com.example.lovecounter.presentation.settings.SettingsScreen
import com.example.lovecounter.presentation.specialday.SpecialDaysScreen
import com.example.lovecounter.presentation.splash.SplashScreen
import com.example.lovecounter.presentation.splash.SplashViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<Screen.Splash> {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreen(
                viewModel = viewModel,
                onNavigate = { destination ->
                    navController.navigate(destination) {
                        popUpTo<Screen.Splash> { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.Onboarding> {
            val viewModel = hiltViewModel<OnboardingViewModel>()
            OnboardingScreen(
                onFinishClicked = {
                    viewModel.completeOnboarding()
                }
            )
        }

        composable<Screen.Home> {
            HomeScreen()
        }

        composable<Screen.Memories> {
            MemoriesScreen()
        }

        composable<Screen.Profile> {
            //ProfileScreen()
        }

        composable<Screen.Settings> {
            SettingsScreen()
        }

        composable<Screen.AddMemory> {
            AddMemoryScreen()
        }

        composable<Screen.SpecialDays> {
            SpecialDaysScreen()
        }
    }
}