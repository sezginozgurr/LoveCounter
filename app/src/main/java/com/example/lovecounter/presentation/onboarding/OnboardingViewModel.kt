package com.example.lovecounter.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.data.repository.DataStoreRepository
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import com.example.lovecounter.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel(),
    MVI<OnboardingContract.UiState, OnboardingContract.UiAction> by mvi(OnboardingContract.UiState()),
    NavigationClient by navigationClient() {

    override fun onAction(uiAction: OnboardingContract.UiAction) {
        when (uiAction) {
            OnboardingContract.UiAction.OnFinishClick -> handleFinishOnboarding()
            is OnboardingContract.UiAction.OnPageChange -> {
                updateUiState { copy(currentPage = uiAction.page) }
            }
        }
    }

    private fun handleFinishOnboarding() {
        updateUiState { copy(isLoading = true) }
        viewModelScope.launch {
            dataStoreRepository.completeOnboarding()
            updateUiState { copy(isLoading = false) }
            navigateTo(Screen.Home)
        }
    }
}
