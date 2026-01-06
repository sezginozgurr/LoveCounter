package com.example.lovecounter.presentation.datingstories

import com.example.lovecounter.data.model.DatingStory

object DatingStoriesContract {
    data class UiState(
        val stories: List<DatingStory> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object OnAddStoryClick : UiAction
        data class OnStoryClick(val story: DatingStory) : UiAction
    }
}
