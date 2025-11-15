package com.example.lovecounter.presentation.specialday

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.lovecounter.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class Event(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val title: String,
    val date: String,
    val time: String,
    @DrawableRes val iconRes: Int
)

data class ProfileUiState(
    val events: List<Event> = emptyList()
)

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadMockEvents()
    }

    private fun loadMockEvents() {
        val mockEvents = listOf(
            Event(1, R.drawable.special_day_1, "Evlilik Yıl Dönümü", "20.05.1993", "13:00", R.drawable.special_day_1),
            Event(2, R.drawable.special_day_2, "Evlilik Yıl Dönümü", "20.05.1993", "13:00", R.drawable.special_day_2),
            Event(3, R.drawable.special_day_3, "Evlilik Yıl Dönümü", "20.05.1993", "13:00", R.drawable.special_day_3),
            Event(4, R.drawable.special_day_4, "Evlilik Yıl Dönümü", "20.05.1993", "13:00", R.drawable.special_day_4)
        )
        _uiState.value = _uiState.value.copy(events = mockEvents)
    }
}
