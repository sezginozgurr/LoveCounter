package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.Recommendation
import kotlinx.coroutines.flow.Flow

/**
 * DAO for Recommendation operations
 */
@Dao
interface RecommendationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendation(recommendation: Recommendation): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendations(recommendations: List<Recommendation>)

    @Update
    suspend fun updateRecommendation(recommendation: Recommendation)

    @Delete
    suspend fun deleteRecommendation(recommendation: Recommendation)

    @Query("SELECT * FROM recommendations ORDER BY likeCount DESC, createdAt DESC")
    fun getAllRecommendations(): Flow<List<Recommendation>>

    @Query("SELECT * FROM recommendations WHERE id = :id")
    suspend fun getRecommendationById(id: Int): Recommendation?

    @Query("SELECT * FROM recommendations WHERE isLiked = 1 ORDER BY updatedAt DESC")
    fun getLikedRecommendations(): Flow<List<Recommendation>>

    @Query("UPDATE recommendations SET isLiked = :isLiked WHERE id = :id")
    suspend fun updateRecommendationLike(id: Int, isLiked: Boolean)

    @Query("DELETE FROM recommendations WHERE id = :id")
    suspend fun deleteRecommendationById(id: Int)

    @Query("DELETE FROM recommendations")
    suspend fun deleteAllRecommendations()

    @Query("SELECT COUNT(*) FROM recommendations")
    fun getRecommendationsCount(): Flow<Int>
}
