package com.example.lovecounter.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import com.example.lovecounter.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel(),
    NavigationClient by navigationClient() {

    private val _uiState = MutableStateFlow(ProfileContract.UiState())
    val uiState: StateFlow<ProfileContract.UiState> = _uiState.asStateFlow()

    init {
        loadProfileData()
    }

    fun onAction(action: ProfileContract.UiAction) {
        when (action) {
            is ProfileContract.UiAction.OnEditProfileClick -> {
                // Handle edit profile
            }

            is ProfileContract.UiAction.OnViewMemoriesClick -> {
                viewModelScope.launch {
                    navigateTo(Screen.Memories)
                }
            }

            is ProfileContract.UiAction.OnViewSpecialDaysClick -> {
                viewModelScope.launch {
                    navigateTo(Screen.SpecialDays)
                }
            }

            is ProfileContract.UiAction.OnViewRecommendationsClick -> {
                viewModelScope.launch {
                    navigateTo(Screen.Recommendations)
                }
            }

            is ProfileContract.UiAction.OnViewDatingStoriesClick -> {
                viewModelScope.launch {
                    navigateTo(Screen.DatingStories)
                }
            }

            is ProfileContract.UiAction.OnSettingsClick -> {
                viewModelScope.launch {
                    navigateTo(Screen.Settings)
                }
            }
        }
    }

    private fun loadProfileData() {
        // Mock data for demonstration
        _uiState.value = ProfileContract.UiState(
            userName = "Merve",
            partnerName = "Özgür",
            relationshipStartDate = "14 Şubat 2020",
            memoriesCount = 15,
            specialDaysCount = 8
        )
    }
}
