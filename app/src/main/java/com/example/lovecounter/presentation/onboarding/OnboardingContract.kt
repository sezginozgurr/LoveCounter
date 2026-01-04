package com.example.lovecounter.presentation.onboarding

object OnboardingContract {
    data class UiState(
        val currentPage: Int = 0,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object OnFinishClick : UiAction
        data class OnPageChange(val page: Int) : UiAction
    }
}
