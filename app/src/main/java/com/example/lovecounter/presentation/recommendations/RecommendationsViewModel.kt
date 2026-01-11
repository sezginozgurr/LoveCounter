package com.example.lovecounter.presentation.recommendations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.R
import com.example.lovecounter.data.model.RecommendationEntity
import com.example.lovecounter.delegation.dialogclient.DialogClient
import com.example.lovecounter.delegation.dialogclient.dialogClient
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    private val repository: com.example.lovecounter.domain.repository.MainRepository,
) : ViewModel(),
    MVI<RecommendationsContract.UiState, RecommendationsContract.UiAction> by mvi(RecommendationsContract.UiState()),
    NavigationClient by navigationClient(),
    DialogClient by dialogClient() {

    init {
        loadRecommendations()
        seedMockDataIfNeeded()
    }

    override fun onAction(uiAction: RecommendationsContract.UiAction) {
        when (uiAction) {
            is RecommendationsContract.UiAction.OnAddRecommendationClick -> Unit
            is RecommendationsContract.UiAction.OnShareClick -> Unit
            is RecommendationsContract.UiAction.OnRecommendationClick -> Unit
            is RecommendationsContract.UiAction.OnLikeClick -> toggleLike(uiAction.recommendationEntity)
        }
    }

    private fun loadRecommendations() {
        viewModelScope.launch {
            repository.getAllRecommendations().collect { recommendations ->
                updateUiState { copy(recommendationEntities = recommendations, isLoading = false) }
            }
        }
    }

    private fun seedMockDataIfNeeded() {
        viewModelScope.launch {
            val existingRecommendations = repository.getAllRecommendations()
            existingRecommendations.collect { recommendations ->
                if (recommendations.isEmpty()) {
                    val mockRecommendations = listOf(
                        RecommendationEntity(
                            coupleName = "Merve & Özgür",
                            duration = "10 Yıl 5 Ay 20 Gün",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                            photoResId = R.drawable.fakephoto
                        ),
                        RecommendationEntity(
                            coupleName = "Ayşe & Mehmet",
                            duration = "5 Yıl 3 Ay 15 Gün",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                            photoResId = R.drawable.fakephoto
                        ),
                        RecommendationEntity(
                            coupleName = "Zeynep & Can",
                            duration = "8 Yıl 7 Ay 10 Gün",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                            photoResId = R.drawable.fakephoto
                        )
                    )
                    repository.insertRecommendations(mockRecommendations)
                }
            }
        }
    }

    private fun toggleLike(recommendationEntity: RecommendationEntity) {
        viewModelScope.launch {
            repository.updateRecommendationLike(recommendationEntity.id, !recommendationEntity.isLiked)
        }
    }
}
