package com.example.lovecounter.presentation.specialday

import androidx.lifecycle.ViewModel
import com.example.lovecounter.R
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialDayViewModel @Inject constructor() : ViewModel(),
    MVI<SpecialDayContract.UiState, SpecialDayContract.UiAction> by mvi(SpecialDayContract.UiState()) {

    init {
        loadMockEvents()
    }

    override fun onAction(uiAction: SpecialDayContract.UiAction) {
        when (uiAction) {
            is SpecialDayContract.UiAction.OnSpecialDayClick -> {
                // Handle special day type selection
            }

            is SpecialDayContract.UiAction.OnEventClick -> {
                // Handle event click
            }
        }
    }

    private fun loadMockEvents() {
        val mockEvents = listOf(
            Event(1, R.drawable.special_day_1, "Evlilik Yıl Dönümü", "20.05.1993", "13:00", R.drawable.special_day_1),
            Event(2, R.drawable.special_day_2, "Evlilik Yıl Dönümü", "20.05.1993", "13:00", R.drawable.special_day_2),
            Event(3, R.drawable.special_day_3, "Evlilik Yıl Dönümü", "20.05.1993", "13:00", R.drawable.special_day_3),
            Event(4, R.drawable.special_day_4, "Evlilik Yıl Dönümü", "20.05.1993", "13:00", R.drawable.special_day_4)
        )
        updateUiState { copy(events = mockEvents) }
    }
}

