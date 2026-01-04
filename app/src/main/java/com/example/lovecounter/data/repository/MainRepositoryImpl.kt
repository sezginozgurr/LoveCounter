package com.example.lovecounter.data.repository

import com.example.lovecounter.data.model.Memory
import com.example.lovecounter.data.source.local.MainRoomDB
import com.example.lovecounter.data.source.remote.MainService
import com.example.lovecounter.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val mainRoomDB: MainRoomDB,
) : MainRepository {

    override suspend fun insertMemory(memory: Memory) {
        mainRoomDB.mainDao().insertMemory(memory)
    }

    override fun getMemories(): Flow<List<Memory>> {
        return mainRoomDB.mainDao().getMemories()
    }
}