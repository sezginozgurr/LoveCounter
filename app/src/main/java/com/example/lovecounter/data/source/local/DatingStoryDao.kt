package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.DatingStory
import kotlinx.coroutines.flow.Flow

/**
 * DAO for DatingStory operations
 */
@Dao
interface DatingStoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDatingStory(story: DatingStory): Long

    @Update
    suspend fun updateDatingStory(story: DatingStory)

    @Delete
    suspend fun deleteDatingStory(story: DatingStory)

    @Query("SELECT * FROM dating_stories ORDER BY dateTimestamp DESC")
    fun getAllDatingStories(): Flow<List<DatingStory>>

    @Query("SELECT * FROM dating_stories WHERE id = :id")
    suspend fun getDatingStoryById(id: Int): DatingStory?

    @Query("SELECT * FROM dating_stories WHERE isFavorite = 1 ORDER BY dateTimestamp DESC")
    fun getFavoriteDatingStories(): Flow<List<DatingStory>>

    @Query("UPDATE dating_stories SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateDatingStoryFavorite(id: Int, isFavorite: Boolean)

    @Query("DELETE FROM dating_stories WHERE id = :id")
    suspend fun deleteDatingStoryById(id: Int)

    @Query("SELECT COUNT(*) FROM dating_stories")
    fun getDatingStoriesCount(): Flow<Int>
}
