package com.example.lovecounter.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class RelationshipDuration(
    val days: Int = 0,
    val months: Int = 0,
    val years: Int = 0,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {

    private val _relationshipDuration = MutableStateFlow(RelationshipDuration())
    val relationshipDuration: StateFlow<RelationshipDuration> = _relationshipDuration.asStateFlow()

    private val _isDateSelected = MutableStateFlow(false)
    val isDateSelected: StateFlow<Boolean> = _isDateSelected.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreRepository.relationshipStartDate.collect { startDateMillis ->
                _isDateSelected.value = startDateMillis != null
                startDateMillis?.let { calculateDuration(Date(it)) }
            }
        }
    }

    fun updateStartDate(date: Date) {
        viewModelScope.launch {
            dataStoreRepository.saveRelationshipStartDate(date)
            calculateDuration(date)
        }
    }

    private fun calculateDuration(startDate: Date) {
        val currentDate = Date()
        val diffInMillis = currentDate.time - startDate.time

        val days = TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
        val months = days / 30
        val years = days / 365

        _relationshipDuration.value = RelationshipDuration(
            days = days % 30,
            months = months % 12,
            years = years
        )
    }
} 