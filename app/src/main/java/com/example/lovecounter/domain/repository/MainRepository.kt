package com.example.lovecounter.domain.repository

import com.example.lovecounter.data.model.Memory
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun insertMemory(memory: Memory)

    fun getMemories(): Flow<List<Memory>>
}