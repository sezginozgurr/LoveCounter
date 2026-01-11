package com.example.lovecounter.presentation.datingstories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.R
import com.example.lovecounter.data.model.DatingStoryEntity
import com.example.lovecounter.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatingStoriesViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DatingStoriesContract.UiState())
    val uiState: StateFlow<DatingStoriesContract.UiState> = _uiState.asStateFlow()

    init {
        loadDatingStories()
        seedMockDataIfNeeded()
    }

    fun onAction(action: DatingStoriesContract.UiAction) {
        when (action) {
            is DatingStoriesContract.UiAction.OnAddStoryClick -> Unit
            is DatingStoriesContract.UiAction.OnStoryClick -> Unit
        }
    }

    private fun loadDatingStories() {
        viewModelScope.launch {
            repository.getAllDatingStories().collect { stories ->
                _uiState.update { it.copy(stories = stories, isLoading = false) }
            }
        }
    }

    private fun seedMockDataIfNeeded() {
        viewModelScope.launch {
            repository.getAllDatingStories().collect { stories ->
                if (stories.isEmpty()) {
                    val mockStories = listOf(
                        DatingStoryEntity(
                            title = "İlk Buluşmamız",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                            photoResId = R.drawable.fakephoto
                        ),
                        DatingStoryEntity(
                            title = "Deniz Kenarında",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                            photoResId = R.drawable.fakephoto
                        ),
                        DatingStoryEntity(
                            title = "Romantik Akşam Yemeği",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                            photoResId = R.drawable.fakephoto
                        ),
                        DatingStoryEntity(
                            title = "Yıldönümü Sürprizi",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                            photoResId = R.drawable.fakephoto
                        ),
                        DatingStoryEntity(
                            title = "Tatil Anıları",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                            photoResId = R.drawable.fakephoto
                        )
                    )
                    mockStories.forEach { repository.insertDatingStory(it) }
                }
            }
        }
    }
}
