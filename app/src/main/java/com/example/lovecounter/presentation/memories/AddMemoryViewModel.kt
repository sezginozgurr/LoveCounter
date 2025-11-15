package com.example.lovecounter.presentation.memories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecounter.data.model.Memory
import com.example.lovecounter.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoryViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun addMemory(memory: Memory) {
        viewModelScope.launch {
            repository.insertMemory(memory)
        }
    }
}