package com.example.lovecounter.domain.repository

import com.example.lovecounter.data.model.DatingStory
import com.example.lovecounter.data.model.Memory
import com.example.lovecounter.data.model.Recommendation
import com.example.lovecounter.data.model.SpecialDay
import com.example.lovecounter.data.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Main repository interface for all database operations
 */
interface MainRepository {

    // User operations
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    fun getUser(): Flow<User?>
    suspend fun getUserOnce(): User?

    // Memory operations
    suspend fun insertMemory(memory: Memory): Long
    suspend fun updateMemory(memory: Memory)
    suspend fun deleteMemory(memory: Memory)
    fun getMemories(): Flow<List<Memory>>
    suspend fun getMemoryById(id: Int): Memory?
    fun getFavoriteMemories(): Flow<List<Memory>>
    suspend fun updateMemoryFavorite(id: Int, isFavorite: Boolean)
    fun getMemoriesCount(): Flow<Int>

    // Special Day operations
    suspend fun insertSpecialDay(specialDay: SpecialDay): Long
    suspend fun updateSpecialDay(specialDay: SpecialDay)
    suspend fun deleteSpecialDay(specialDay: SpecialDay)
    fun getAllSpecialDays(): Flow<List<SpecialDay>>
    suspend fun getSpecialDayById(id: Int): SpecialDay?
    fun getSpecialDaysByCategory(category: String): Flow<List<SpecialDay>>
    fun getSpecialDaysCount(): Flow<Int>

    // Dating Story operations
    suspend fun insertDatingStory(story: DatingStory): Long
    suspend fun updateDatingStory(story: DatingStory)
    suspend fun deleteDatingStory(story: DatingStory)
    fun getAllDatingStories(): Flow<List<DatingStory>>
    suspend fun getDatingStoryById(id: Int): DatingStory?
    fun getFavoriteDatingStories(): Flow<List<DatingStory>>
    fun getDatingStoriesCount(): Flow<Int>

    // Recommendation operations
    suspend fun insertRecommendation(recommendation: Recommendation): Long
    suspend fun insertRecommendations(recommendations: List<Recommendation>)
    suspend fun updateRecommendation(recommendation: Recommendation)
    suspend fun deleteRecommendation(recommendation: Recommendation)
    fun getAllRecommendations(): Flow<List<Recommendation>>
    suspend fun getRecommendationById(id: Int): Recommendation?
    fun getLikedRecommendations(): Flow<List<Recommendation>>
    suspend fun updateRecommendationLike(id: Int, isLiked: Boolean)
    suspend fun clearRecommendations()
}