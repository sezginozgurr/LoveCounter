package com.example.lovecounter.presentation.splash

import com.example.lovecounter.presentation.navigation.Screen

object SplashContract {
    data class UiState(
        val isLoading: Boolean = true,
        val startDestination: Screen? = null,
    )

    sealed interface UiAction {
        data object CheckOnboardingStatus : UiAction
    }
}
