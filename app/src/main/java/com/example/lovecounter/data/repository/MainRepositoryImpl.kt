package com.example.lovecounter.data.repository

import com.example.lovecounter.data.model.DatingStoryEntity
import com.example.lovecounter.data.model.MemoryEntity
import com.example.lovecounter.data.model.RecommendationEntity
import com.example.lovecounter.data.model.SpecialDayEntity
import com.example.lovecounter.data.model.UserEntity
import com.example.lovecounter.data.source.local.MainRoomDB
import com.example.lovecounter.data.source.remote.MainService
import com.example.lovecounter.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of MainRepository with complete database operations
 */
class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val mainRoomDB: MainRoomDB,
) : MainRepository {

    // UserEntity operations
    override suspend fun insertUser(userEntity: UserEntity) {
        mainRoomDB.userDao().insertUser(userEntity)
    }

    override suspend fun updateUser(userEntity: UserEntity) {
        mainRoomDB.userDao().updateUser(userEntity)
    }

    override fun getUser(): Flow<UserEntity?> {
        return mainRoomDB.userDao().getUser()
    }

    override suspend fun getUserOnce(): UserEntity? {
        return mainRoomDB.userDao().getUserOnce()
    }

    // MemoryEntity operations
    override suspend fun insertMemory(memoryEntity: MemoryEntity): Long {
        return mainRoomDB.memoryDao().insertMemory(memoryEntity)
    }

    override suspend fun updateMemory(memoryEntity: MemoryEntity) {
        mainRoomDB.memoryDao().updateMemory(memoryEntity)
    }

    override suspend fun deleteMemory(memoryEntity: MemoryEntity) {
        mainRoomDB.memoryDao().deleteMemory(memoryEntity)
    }

    override fun getMemories(): Flow<List<MemoryEntity>> {
        return mainRoomDB.memoryDao().getAllMemories()
    }

    override suspend fun getMemoryById(id: Int): MemoryEntity? {
        return mainRoomDB.memoryDao().getMemoryById(id)
    }

    override fun getFavoriteMemories(): Flow<List<MemoryEntity>> {
        return mainRoomDB.memoryDao().getFavoriteMemories()
    }

    override suspend fun updateMemoryFavorite(id: Int, isFavorite: Boolean) {
        mainRoomDB.memoryDao().updateMemoryFavorite(id, isFavorite)
    }

    override fun getMemoriesCount(): Flow<Int> {
        return mainRoomDB.memoryDao().getMemoriesCount()
    }

    // Special Day operations
    override suspend fun insertSpecialDay(specialDayEntity: SpecialDayEntity): Long {
        return mainRoomDB.specialDayDao().insertSpecialDay(specialDayEntity)
    }

    override suspend fun updateSpecialDay(specialDayEntity: SpecialDayEntity) {
        mainRoomDB.specialDayDao().updateSpecialDay(specialDayEntity)
    }

    override suspend fun deleteSpecialDay(specialDayEntity: SpecialDayEntity) {
        mainRoomDB.specialDayDao().deleteSpecialDay(specialDayEntity)
    }

    override fun getAllSpecialDays(): Flow<List<SpecialDayEntity>> {
        return mainRoomDB.specialDayDao().getAllSpecialDays()
    }

    override suspend fun getSpecialDayById(id: Int): SpecialDayEntity? {
        return mainRoomDB.specialDayDao().getSpecialDayById(id)
    }

    override fun getSpecialDaysByCategory(category: String): Flow<List<SpecialDayEntity>> {
        return mainRoomDB.specialDayDao().getSpecialDaysByCategory(category)
    }

    override fun getSpecialDaysCount(): Flow<Int> {
        return mainRoomDB.specialDayDao().getSpecialDaysCount()
    }

    // Dating Story operations
    override suspend fun insertDatingStory(story: DatingStoryEntity): Long {
        return mainRoomDB.datingStoryDao().insertDatingStory(story)
    }

    override suspend fun updateDatingStory(story: DatingStoryEntity) {
        mainRoomDB.datingStoryDao().updateDatingStory(story)
    }

    override suspend fun deleteDatingStory(story: DatingStoryEntity) {
        mainRoomDB.datingStoryDao().deleteDatingStory(story)
    }

    override fun getAllDatingStories(): Flow<List<DatingStoryEntity>> {
        return mainRoomDB.datingStoryDao().getAllDatingStories()
    }

    override suspend fun getDatingStoryById(id: Int): DatingStoryEntity? {
        return mainRoomDB.datingStoryDao().getDatingStoryById(id)
    }

    override fun getFavoriteDatingStories(): Flow<List<DatingStoryEntity>> {
        return mainRoomDB.datingStoryDao().getFavoriteDatingStories()
    }

    override fun getDatingStoriesCount(): Flow<Int> {
        return mainRoomDB.datingStoryDao().getDatingStoriesCount()
    }

    // RecommendationEntity operations
    override suspend fun insertRecommendation(recommendationEntity: RecommendationEntity): Long {
        return mainRoomDB.recommendationDao().insertRecommendation(recommendationEntity)
    }

    override suspend fun insertRecommendations(recommendationEntities: List<RecommendationEntity>) {
        mainRoomDB.recommendationDao().insertRecommendations(recommendationEntities)
    }

    override suspend fun updateRecommendation(recommendationEntity: RecommendationEntity) {
        mainRoomDB.recommendationDao().updateRecommendation(recommendationEntity)
    }

    override suspend fun deleteRecommendation(recommendationEntity: RecommendationEntity) {
        mainRoomDB.recommendationDao().deleteRecommendation(recommendationEntity)
    }

    override fun getAllRecommendations(): Flow<List<RecommendationEntity>> {
        return mainRoomDB.recommendationDao().getAllRecommendations()
    }

    override suspend fun getRecommendationById(id: Int): RecommendationEntity? {
        return mainRoomDB.recommendationDao().getRecommendationById(id)
    }

    override fun getLikedRecommendations(): Flow<List<RecommendationEntity>> {
        return mainRoomDB.recommendationDao().getLikedRecommendations()
    }

    override suspend fun updateRecommendationLike(id: Int, isLiked: Boolean) {
        mainRoomDB.recommendationDao().updateRecommendationLike(id, isLiked)
    }

    override suspend fun clearRecommendations() {
        mainRoomDB.recommendationDao().deleteAllRecommendations()
    }
}