package com.example.lovecounter.ui.home

object OnboardingContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList()
    )

    sealed class UiAction

    sealed class UiEffect
}