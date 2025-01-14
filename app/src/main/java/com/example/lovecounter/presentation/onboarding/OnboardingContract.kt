package com.example.lovecounter.presentation.onboarding

object OnboardingContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList()
    )

    sealed class UiAction

    sealed class UiEffect
}