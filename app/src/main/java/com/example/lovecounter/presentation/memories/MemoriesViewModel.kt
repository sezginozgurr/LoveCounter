package com.example.lovecounter.presentation.memories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import com.example.lovecounter.domain.repository.MainRepository
import com.example.lovecounter.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoriesViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel(),
    MVI<MemoriesContract.UiState, MemoriesContract.UiAction> by mvi(MemoriesContract.UiState()),
    NavigationClient by navigationClient() {

    init {
        loadMemories()
    }

    override fun onAction(uiAction: MemoriesContract.UiAction) {
        when (uiAction) {
            MemoriesContract.UiAction.OnAddMemoryClick -> viewModelScope.launch {
                navigateTo(Screen.AddMemory)
            }

            is MemoriesContract.UiAction.OnMemoryClick -> {
                // Navigate to memory detail if implemented
            }
        }
    }

    private fun loadMemories() {
        viewModelScope.launch {
            repository.getMemories().collect { memories ->
                updateUiState { copy(memories = memories, isLoading = false) }
            }
        }
    }
}
