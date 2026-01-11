package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.DatingStoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for DatingStoryEntity operations
 */
@Dao
interface DatingStoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDatingStory(story: DatingStoryEntity): Long

    @Update
    suspend fun updateDatingStory(story: DatingStoryEntity)

    @Delete
    suspend fun deleteDatingStory(story: DatingStoryEntity)

    @Query("SELECT * FROM dating_stories ORDER BY dateTimestamp DESC")
    fun getAllDatingStories(): Flow<List<DatingStoryEntity>>

    @Query("SELECT * FROM dating_stories WHERE id = :id")
    suspend fun getDatingStoryById(id: Int): DatingStoryEntity?

    @Query("SELECT * FROM dating_stories WHERE isFavorite = 1 ORDER BY dateTimestamp DESC")
    fun getFavoriteDatingStories(): Flow<List<DatingStoryEntity>>

    @Query("UPDATE dating_stories SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateDatingStoryFavorite(id: Int, isFavorite: Boolean)

    @Query("DELETE FROM dating_stories WHERE id = :id")
    suspend fun deleteDatingStoryById(id: Int)

    @Query("SELECT COUNT(*) FROM dating_stories")
    fun getDatingStoriesCount(): Flow<Int>
}
