package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.RecommendationEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for RecommendationEntity operations
 */
@Dao
interface RecommendationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendation(recommendationEntity: RecommendationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendations(recommendationEntities: List<RecommendationEntity>)

    @Update
    suspend fun updateRecommendation(recommendationEntity: RecommendationEntity)

    @Delete
    suspend fun deleteRecommendation(recommendationEntity: RecommendationEntity)

    @Query("SELECT * FROM recommendationEntities ORDER BY likeCount DESC, createdAt DESC")
    fun getAllRecommendations(): Flow<List<RecommendationEntity>>

    @Query("SELECT * FROM recommendationEntities WHERE id = :id")
    suspend fun getRecommendationById(id: Int): RecommendationEntity?

    @Query("SELECT * FROM recommendationEntities WHERE isLiked = 1 ORDER BY updatedAt DESC")
    fun getLikedRecommendations(): Flow<List<RecommendationEntity>>

    @Query("UPDATE recommendationEntities SET isLiked = :isLiked WHERE id = :id")
    suspend fun updateRecommendationLike(id: Int, isLiked: Boolean)

    @Query("DELETE FROM recommendationEntities WHERE id = :id")
    suspend fun deleteRecommendationById(id: Int)

    @Query("DELETE FROM recommendationEntities")
    suspend fun deleteAllRecommendations()

    @Query("SELECT COUNT(*) FROM recommendationEntities")
    fun getRecommendationsCount(): Flow<Int>
}
