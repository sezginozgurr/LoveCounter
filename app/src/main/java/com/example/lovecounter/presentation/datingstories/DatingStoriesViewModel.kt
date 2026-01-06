package com.example.lovecounter.presentation.datingstories

import androidx.lifecycle.ViewModel
import com.example.lovecounter.R
import com.example.lovecounter.data.model.DatingStory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DatingStoriesViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(DatingStoriesContract.UiState())
    val uiState: StateFlow<DatingStoriesContract.UiState> = _uiState.asStateFlow()

    init {
        loadDatingStories()
    }

    fun onAction(action: DatingStoriesContract.UiAction) {
        when (action) {
            is DatingStoriesContract.UiAction.OnAddStoryClick -> {
                // Handle add story
            }

            is DatingStoriesContract.UiAction.OnStoryClick -> {
                // Handle story click
            }
        }
    }

    private fun loadDatingStories() {
        // Mock data for demonstration
        val mockStories = listOf(
            DatingStory(
                id = 1,
                title = "Buraya Başlık Gelecek",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                photoResId = R.drawable.fakephoto
            ),
            DatingStory(
                id = 2,
                title = "Buraya Başlık Gelecek",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                photoResId = R.drawable.fakephoto
            ),
            DatingStory(
                id = 3,
                title = "Buraya Başlık Gelecek",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                photoResId = R.drawable.fakephoto
            ),
            DatingStory(
                id = 4,
                title = "Buraya Başlık Gelecek",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                photoResId = R.drawable.fakephoto
            ),
            DatingStory(
                id = 5,
                title = "Buraya Başlık Gelecek",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                photoResId = R.drawable.fakephoto
            )
        )
        _uiState.update { it.copy(stories = mockStories) }
    }
}
