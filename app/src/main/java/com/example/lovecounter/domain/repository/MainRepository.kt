package com.example.lovecounter.domain.repository

import com.example.lovecounter.data.model.DatingStoryEntity
import com.example.lovecounter.data.model.MemoryEntity
import com.example.lovecounter.data.model.RecommendationEntity
import com.example.lovecounter.data.model.SpecialDayEntity
import com.example.lovecounter.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Main repository interface for all database operations
 */
interface MainRepository {

    suspend fun insertUser(userEntity: UserEntity)
    suspend fun updateUser(userEntity: UserEntity)
    fun getUser(): Flow<UserEntity?>
    suspend fun getUserOnce(): UserEntity?

    suspend fun insertMemory(memoryEntity: MemoryEntity): Long
    suspend fun updateMemory(memoryEntity: MemoryEntity)
    suspend fun deleteMemory(memoryEntity: MemoryEntity)
    fun getMemories(): Flow<List<MemoryEntity>>
    suspend fun getMemoryById(id: Int): MemoryEntity?
    fun getFavoriteMemories(): Flow<List<MemoryEntity>>
    suspend fun updateMemoryFavorite(id: Int, isFavorite: Boolean)
    fun getMemoriesCount(): Flow<Int>

    suspend fun insertSpecialDay(specialDayEntity: SpecialDayEntity): Long
    suspend fun updateSpecialDay(specialDayEntity: SpecialDayEntity)
    suspend fun deleteSpecialDay(specialDayEntity: SpecialDayEntity)
    fun getAllSpecialDays(): Flow<List<SpecialDayEntity>>
    suspend fun getSpecialDayById(id: Int): SpecialDayEntity?
    fun getSpecialDaysByCategory(category: String): Flow<List<SpecialDayEntity>>
    fun getSpecialDaysCount(): Flow<Int>

    suspend fun insertDatingStory(story: DatingStoryEntity): Long
    suspend fun updateDatingStory(story: DatingStoryEntity)
    suspend fun deleteDatingStory(story: DatingStoryEntity)
    fun getAllDatingStories(): Flow<List<DatingStoryEntity>>
    suspend fun getDatingStoryById(id: Int): DatingStoryEntity?
    fun getFavoriteDatingStories(): Flow<List<DatingStoryEntity>>
    fun getDatingStoriesCount(): Flow<Int>

    suspend fun insertRecommendation(recommendationEntity: RecommendationEntity): Long
    suspend fun insertRecommendations(recommendationEntities: List<RecommendationEntity>)
    suspend fun updateRecommendation(recommendationEntity: RecommendationEntity)
    suspend fun deleteRecommendation(recommendationEntity: RecommendationEntity)
    fun getAllRecommendations(): Flow<List<RecommendationEntity>>
    suspend fun getRecommendationById(id: Int): RecommendationEntity?
    fun getLikedRecommendations(): Flow<List<RecommendationEntity>>
    suspend fun updateRecommendationLike(id: Int, isLiked: Boolean)
    suspend fun clearRecommendations()
}