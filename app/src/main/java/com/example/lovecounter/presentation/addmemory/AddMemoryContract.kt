package com.example.lovecounter.presentation.addmemory

import android.net.Uri

object AddMemoryContract {
    data class UiState(
        val title: String = "",
        val subtitle: String = "",
        val selectedImageUris: List<Uri> = emptyList(),
        val isSaving: Boolean = false,
    )

    sealed interface UiAction {
        data class OnTitleChange(val title: String) : UiAction
        data class OnSubtitleChange(val subtitle: String) : UiAction
        data class OnImagesSelected(val uris: List<Uri>) : UiAction
        data object OnPickImagesClick : UiAction
        data object OnSaveClick : UiAction
    }
}
