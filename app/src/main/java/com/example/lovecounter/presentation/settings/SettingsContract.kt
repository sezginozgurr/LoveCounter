package com.example.lovecounter.presentation.settings

object SettingsContract {
    data class UiState(
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object OnAddMemoryClick : UiAction
        data class OnMemoryItemClick(val itemId: Int) : UiAction
    }
}
