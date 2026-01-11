package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.MemoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for MemoryEntity operations
 */
@Dao
interface MemoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemory(memoryEntity: MemoryEntity): Long

    @Update
    suspend fun updateMemory(memoryEntity: MemoryEntity)

    @Delete
    suspend fun deleteMemory(memoryEntity: MemoryEntity)

    @Query("SELECT * FROM memories ORDER BY dateTimestamp DESC")
    fun getAllMemories(): Flow<List<MemoryEntity>>

    @Query("SELECT * FROM memories WHERE id = :id")
    suspend fun getMemoryById(id: Int): MemoryEntity?

    @Query("SELECT * FROM memories WHERE isFavorite = 1 ORDER BY dateTimestamp DESC")
    fun getFavoriteMemories(): Flow<List<MemoryEntity>>

    @Query("UPDATE memories SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateMemoryFavorite(id: Int, isFavorite: Boolean)

    @Query("DELETE FROM memories WHERE id = :id")
    suspend fun deleteMemoryById(id: Int)

    @Query("SELECT COUNT(*) FROM memories")
    fun getMemoriesCount(): Flow<Int>

    @Query("SELECT * FROM memories WHERE dateTimestamp BETWEEN :startDate AND :endDate ORDER BY dateTimestamp DESC")
    fun getMemoriesByDateRange(startDate: Long, endDate: Long): Flow<List<MemoryEntity>>
}
