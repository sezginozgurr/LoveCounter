package com.example.lovecounter.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import com.example.lovecounter.domain.repository.MainRepository
import com.example.lovecounter.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel(),
    NavigationClient by navigationClient() {

    private val _uiState = MutableStateFlow(ProfileContract.UiState())
    val uiState: StateFlow<ProfileContract.UiState> = _uiState.asStateFlow()

    init {
        loadProfileData()
        loadCounts()
    }

    fun onAction(action: ProfileContract.UiAction) {
        when (action) {
            is ProfileContract.UiAction.OnEditProfileClick -> Unit
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
        viewModelScope.launch {
            repository.getUser().collect { userEntity ->
                userEntity?.let { user ->
                    val formattedDate = user.relationshipStartDate?.let {
                        formatDate(it)
                    } ?: "Tarih belirlenmedi"

                    _uiState.value = _uiState.value.copy(
                        userName = user.userName.ifBlank { "Kullanıcı" },
                        partnerName = user.partnerName.ifBlank { "Partner" },
                        relationshipStartDate = formattedDate
                    )
                }
            }
        }
    }

    private fun loadCounts() {
        viewModelScope.launch {
            repository.getMemoriesCount().collect { count ->
                _uiState.value = _uiState.value.copy(memoriesCount = count)
            }
        }

        viewModelScope.launch {
            repository.getSpecialDaysCount().collect { count ->
                _uiState.value = _uiState.value.copy(specialDaysCount = count)
            }
        }
    }

    private fun formatDate(timestamp: Long): String {
        val date = Date(timestamp)
        val format = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
        return format.format(date)
    }
}
