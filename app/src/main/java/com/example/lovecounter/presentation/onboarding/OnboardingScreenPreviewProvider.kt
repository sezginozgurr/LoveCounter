package com.example.lovecounter.presentation.onboarding

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class OnboardingScreenPreviewProvider : PreviewParameterProvider<OnboardingContract.UiState> {
    override val values: Sequence<OnboardingContract.UiState>
        get() = sequenceOf(
            OnboardingContract.UiState(
                isLoading = true,
            ),
            OnboardingContract.UiState(
                isLoading = false,
            ),
            OnboardingContract.UiState(
                isLoading = false,
            ),
        )
}