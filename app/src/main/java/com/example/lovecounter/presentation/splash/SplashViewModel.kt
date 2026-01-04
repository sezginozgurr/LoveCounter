package com.example.lovecounter.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.data.repository.DataStoreRepository
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import com.example.lovecounter.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel(),
    MVI<SplashContract.UiState, SplashContract.UiAction> by mvi(SplashContract.UiState()),
    NavigationClient by navigationClient() {

    init {
        checkOnboardingStatus()
    }

    override fun onAction(uiAction: SplashContract.UiAction) {
        when (uiAction) {
            SplashContract.UiAction.CheckOnboardingStatus -> checkOnboardingStatus()
        }
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            delay(1000) // Splash delay
            dataStoreRepository.isOnboardingCompleted.collect { completed ->
                val destination = if (completed) Screen.Home else Screen.Onboarding
                updateUiState { copy(startDestination = destination, isLoading = false) }
                navigateTo(destination)
            }
        }
    }
}
