package com.example.lovecounter.presentation.addmemory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.data.model.MemoryEntity
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
            is AddMemoryContract.UiAction.OnCategorySelected -> {
                updateUiState { copy(selectedCategory = uiAction.category) }
            }

            is AddMemoryContract.UiAction.OnDateSelected -> {
                updateUiState { copy(selectedDate = uiAction.date) }
            }

            is AddMemoryContract.UiAction.OnDescriptionChange -> {
                updateUiState { copy(description = uiAction.description) }
            }

            AddMemoryContract.UiAction.OnSaveClick -> handleSaveMemory()
        }
    }

    private fun handleSaveMemory() {
        val currentState = uiState.value
        if (currentState.selectedCategory == null || currentState.description.isBlank()) return

        updateUiState { copy(isSaving = true) }

        viewModelScope.launch {
            val memoryEntity = MemoryEntity(
                title = currentState.selectedCategory.title,
                subtitle = currentState.description,
                photoUris = emptyList()
            )
            repository.insertMemory(memoryEntity)
            updateUiState { copy(isSaving = false) }
            navigateBack()
        }
    }
}
