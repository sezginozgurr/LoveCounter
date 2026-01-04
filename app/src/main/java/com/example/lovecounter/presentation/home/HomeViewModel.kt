package com.example.lovecounter.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.data.repository.DataStoreRepository
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel(),
    MVI<HomeContract.UiState, HomeContract.UiAction> by mvi(HomeContract.UiState()),
    NavigationClient by navigationClient() {

    init {
        viewModelScope.launch {
            dataStoreRepository.relationshipStartDate.collect { startDateMillis ->
                updateUiState { copy(isDateSelected = startDateMillis != null) }
                startDateMillis?.let { calculateDuration(Date(it)) }
            }
        }
    }

    override fun onAction(uiAction: HomeContract.UiAction) {
        when (uiAction) {
            HomeContract.UiAction.OnSelectDateClick -> updateUiState { copy(showDatePicker = true) }
            HomeContract.UiAction.OnDismissDatePicker -> updateUiState { copy(showDatePicker = false) }
            HomeContract.UiAction.OnDismissFemaleImagePicker -> updateUiState { copy(showFemaleImagePicker = false) }
            HomeContract.UiAction.OnDismissMaleImagePicker -> updateUiState { copy(showMaleImagePicker = false) }
            HomeContract.UiAction.OnFemaleImageClick -> updateUiState { copy(showFemaleImagePicker = true) }
            HomeContract.UiAction.OnMaleImageClick -> updateUiState { copy(showMaleImagePicker = true) }
            is HomeContract.UiAction.OnFemaleImageSelected -> {
                updateUiState { copy(femaleImage = uiAction.image, showFemaleImagePicker = false) }
            }

            is HomeContract.UiAction.OnMaleImageSelected -> {
                updateUiState { copy(maleImage = uiAction.image, showMaleImagePicker = false) }
            }

            is HomeContract.UiAction.OnDateSelected -> {
                updateUiState { copy(showDatePicker = false) }
                updateStartDate(Date(uiAction.date))
            }
        }
    }

    private fun updateStartDate(date: Date) {
        viewModelScope.launch {
            dataStoreRepository.saveRelationshipStartDate(date)
            calculateDuration(date)
        }
    }

    private fun calculateDuration(startDate: Date) {
        val currentDate = Date()
        val diffInMillis = currentDate.time - startDate.time

        val totalDays = TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
        val years = totalDays / 365
        val remainingDaysAfterYears = totalDays % 365
        val months = remainingDaysAfterYears / 30
        val days = remainingDaysAfterYears % 30
        val duration = RelationshipDuration(days, months, years)

        updateUiState { copy(relationshipDuration = duration) }
    }
} 