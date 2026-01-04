package com.example.lovecounter.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import com.example.lovecounter.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel(),
    MVI<SettingsContract.UiState, SettingsContract.UiAction> by mvi(SettingsContract.UiState()),
    NavigationClient by navigationClient() {

    override fun onAction(uiAction: SettingsContract.UiAction) {
        when (uiAction) {
            SettingsContract.UiAction.OnAddMemoryClick -> viewModelScope.launch {
                navigateTo(Screen.AddMemory)
            }

            is SettingsContract.UiAction.OnMemoryItemClick -> {
                // Navigate to memory detail or handle item click
            }
        }
    }
}
 