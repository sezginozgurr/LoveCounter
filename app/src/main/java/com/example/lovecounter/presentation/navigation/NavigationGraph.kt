package com.example.lovecounter.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lovecounter.delegation.navigator.LocalNavHostController
import com.example.lovecounter.delegation.navigator.NavigationClientCollector
import com.example.lovecounter.presentation.components.CustomBottomNavigation
import com.example.lovecounter.presentation.home.HomeScreen
import com.example.lovecounter.presentation.home.HomeViewModel
import com.example.lovecounter.presentation.addmemory.AddMemoryScreen
import com.example.lovecounter.presentation.memories.MemoriesScreen
import com.example.lovecounter.presentation.onboarding.OnboardingScreen
import com.example.lovecounter.presentation.onboarding.OnboardingViewModel
import com.example.lovecounter.presentation.settings.SettingsScreen
import com.example.lovecounter.presentation.specialday.SpecialDaysScreen
import com.example.lovecounter.presentation.splash.SplashScreen
import com.example.lovecounter.presentation.splash.SplashViewModel

private const val DURATION = 500

@Composable
fun NavigationGraph() {
    val bottomNavScreens = setOf(
        Screen.Home::class.qualifiedName,
        Screen.SpecialDays::class.qualifiedName,
        Screen.Profile::class.qualifiedName,
        Screen.Settings::class.qualifiedName,
    )

    val enterAnim = fadeIn(tween(DURATION))
    val exitAnim = fadeOut(tween(DURATION))
    val navController = LocalNavHostController.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in bottomNavScreens

    Scaffold(
        containerColor = Color(0xFFF5F5F5),
        contentColor = Color.Black,
        bottomBar = { if (showBottomBar) CustomBottomNavigation() }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            startDestination = Screen.Splash,
            enterTransition = { enterAnim },
            exitTransition = { exitAnim },
            popEnterTransition = { enterAnim },
            popExitTransition = { exitAnim },
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
                val viewModel = hiltViewModel<HomeViewModel>()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                NavigationClientCollector(viewModel.navigationClientEffect)
                HomeScreen(
                    uiState = uiState,
                    onAction = viewModel::onAction,
                )
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
}