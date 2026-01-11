package com.example.lovecounter.presentation.specialday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.R
import com.example.lovecounter.data.model.SpecialDayEntity
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SpecialDayViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel(),
    MVI<SpecialDayContract.UiState, SpecialDayContract.UiAction> by mvi(SpecialDayContract.UiState()) {

    init {
        loadSpecialDays()
        seedMockDataIfNeeded()
    }

    override fun onAction(uiAction: SpecialDayContract.UiAction) {
        when (uiAction) {
            is SpecialDayContract.UiAction.OnSpecialDayClick -> Unit
            is SpecialDayContract.UiAction.OnEventClick -> Unit
        }
    }

    private fun loadSpecialDays() {
        viewModelScope.launch {
            repository.getAllSpecialDays().collect { specialDays ->
                val events = specialDays.map { specialDay ->
                    Event(
                        id = specialDay.id,
                        imageRes = specialDay.iconResId ?: R.drawable.special_day_1,
                        title = specialDay.title,
                        date = formatDate(specialDay.dateTimestamp),
                        time = formatTime(specialDay.timeInMillis),
                        iconRes = specialDay.iconResId ?: R.drawable.special_day_1
                    )
                }
                updateUiState { copy(events = events, isLoading = false) }
            }
        }
    }

    private fun seedMockDataIfNeeded() {
        viewModelScope.launch {
            repository.getAllSpecialDays().collect { specialDays ->
                if (specialDays.isEmpty()) {
                    val mockSpecialDays = listOf(
                        SpecialDayEntity(
                            title = "Evlilik Yıl Dönümü",
                            dateTimestamp = System.currentTimeMillis(),
                            timeInMillis = System.currentTimeMillis(),
                            category = "ANNIVERSARY",
                            iconResId = R.drawable.special_day_1
                        ),
                        SpecialDayEntity(
                            title = "İlk Buluşma Yıl Dönümü",
                            dateTimestamp = System.currentTimeMillis(),
                            timeInMillis = System.currentTimeMillis(),
                            category = "MEETING_ANNIVERSARY",
                            iconResId = R.drawable.special_day_2
                        ),
                        SpecialDayEntity(
                            title = "Doğum Günü",
                            dateTimestamp = System.currentTimeMillis(),
                            timeInMillis = System.currentTimeMillis(),
                            category = "BIRTHDAY",
                            iconResId = R.drawable.special_day_3
                        ),
                        SpecialDayEntity(
                            title = "Özel Gün",
                            dateTimestamp = System.currentTimeMillis(),
                            timeInMillis = System.currentTimeMillis(),
                            category = "OTHER",
                            iconResId = R.drawable.special_day_4
                        )
                    )
                    mockSpecialDays.forEach { repository.insertSpecialDay(it) }
                }
            }
        }
    }

    private fun formatDate(timestamp: Long): String {
        val date = Date(timestamp)
        val format = SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault())
        return format.format(date)
    }

    private fun formatTime(timestamp: Long?): String {
        if (timestamp == null) return "00:00"
        val date = Date(timestamp)
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        return format.format(date)
    }
}

