package com.example.lovecounter.presentation.addmemory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.data.model.Memory
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import com.example.lovecounter.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoryViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel(),
    MVI<AddMemoryContract.UiState, AddMemoryContract.UiAction> by mvi(AddMemoryContract.UiState()),
    NavigationClient by navigationClient() {

    override fun onAction(uiAction: AddMemoryContract.UiAction) {
        when (uiAction) {
            is AddMemoryContract.UiAction.OnTitleChange -> {
                updateUiState { copy(title = uiAction.title) }
            }

            is AddMemoryContract.UiAction.OnSubtitleChange -> {
                updateUiState { copy(subtitle = uiAction.subtitle) }
            }

            is AddMemoryContract.UiAction.OnImagesSelected -> {
                updateUiState { copy(selectedImageUris = uiAction.uris) }
            }

            AddMemoryContract.UiAction.OnPickImagesClick -> {
                // This will be handled by the screen's launcher
            }

            AddMemoryContract.UiAction.OnSaveClick -> handleSaveMemory()
        }
    }

    private fun handleSaveMemory() {
        val currentState = uiState.value
        if (currentState.title.isBlank()) return

        updateUiState { copy(isSaving = true) }

        viewModelScope.launch {
            val memory = Memory(
                title = currentState.title,
                subtitle = currentState.subtitle,
                photoUris = currentState.selectedImageUris.map { it.toString() }
            )
            repository.insertMemory(memory)
            updateUiState { copy(isSaving = false) }
            navigateBack()
        }
    }
}
