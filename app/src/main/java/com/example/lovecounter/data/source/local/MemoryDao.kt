package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.Memory
import kotlinx.coroutines.flow.Flow

/**
 * DAO for Memory operations
 */
@Dao
interface MemoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemory(memory: Memory): Long

    @Update
    suspend fun updateMemory(memory: Memory)

    @Delete
    suspend fun deleteMemory(memory: Memory)

    @Query("SELECT * FROM memories ORDER BY dateTimestamp DESC")
    fun getAllMemories(): Flow<List<Memory>>

    @Query("SELECT * FROM memories WHERE id = :id")
    suspend fun getMemoryById(id: Int): Memory?

    @Query("SELECT * FROM memories WHERE isFavorite = 1 ORDER BY dateTimestamp DESC")
    fun getFavoriteMemories(): Flow<List<Memory>>

    @Query("UPDATE memories SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateMemoryFavorite(id: Int, isFavorite: Boolean)

    @Query("DELETE FROM memories WHERE id = :id")
    suspend fun deleteMemoryById(id: Int)

    @Query("SELECT COUNT(*) FROM memories")
    fun getMemoriesCount(): Flow<Int>

    @Query("SELECT * FROM memories WHERE dateTimestamp BETWEEN :startDate AND :endDate ORDER BY dateTimestamp DESC")
    fun getMemoriesByDateRange(startDate: Long, endDate: Long): Flow<List<Memory>>
}
