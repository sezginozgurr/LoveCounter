package com.example.lovecounter.data.repository

import com.example.lovecounter.data.model.DatingStory
import com.example.lovecounter.data.model.Memory
import com.example.lovecounter.data.model.Recommendation
import com.example.lovecounter.data.model.SpecialDay
import com.example.lovecounter.data.model.User
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

    // User operations
    override suspend fun insertUser(user: User) {
        mainRoomDB.userDao().insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        mainRoomDB.userDao().updateUser(user)
    }

    override fun getUser(): Flow<User?> {
        return mainRoomDB.userDao().getUser()
    }

    override suspend fun getUserOnce(): User? {
        return mainRoomDB.userDao().getUserOnce()
    }

    // Memory operations
    override suspend fun insertMemory(memory: Memory): Long {
        return mainRoomDB.memoryDao().insertMemory(memory)
    }

    override suspend fun updateMemory(memory: Memory) {
        mainRoomDB.memoryDao().updateMemory(memory)
    }

    override suspend fun deleteMemory(memory: Memory) {
        mainRoomDB.memoryDao().deleteMemory(memory)
    }

    override fun getMemories(): Flow<List<Memory>> {
        return mainRoomDB.memoryDao().getAllMemories()
    }

    override suspend fun getMemoryById(id: Int): Memory? {
        return mainRoomDB.memoryDao().getMemoryById(id)
    }

    override fun getFavoriteMemories(): Flow<List<Memory>> {
        return mainRoomDB.memoryDao().getFavoriteMemories()
    }

    override suspend fun updateMemoryFavorite(id: Int, isFavorite: Boolean) {
        mainRoomDB.memoryDao().updateMemoryFavorite(id, isFavorite)
    }

    override fun getMemoriesCount(): Flow<Int> {
        return mainRoomDB.memoryDao().getMemoriesCount()
    }

    // Special Day operations
    override suspend fun insertSpecialDay(specialDay: SpecialDay): Long {
        return mainRoomDB.specialDayDao().insertSpecialDay(specialDay)
    }

    override suspend fun updateSpecialDay(specialDay: SpecialDay) {
        mainRoomDB.specialDayDao().updateSpecialDay(specialDay)
    }

    override suspend fun deleteSpecialDay(specialDay: SpecialDay) {
        mainRoomDB.specialDayDao().deleteSpecialDay(specialDay)
    }

    override fun getAllSpecialDays(): Flow<List<SpecialDay>> {
        return mainRoomDB.specialDayDao().getAllSpecialDays()
    }

    override suspend fun getSpecialDayById(id: Int): SpecialDay? {
        return mainRoomDB.specialDayDao().getSpecialDayById(id)
    }

    override fun getSpecialDaysByCategory(category: String): Flow<List<SpecialDay>> {
        return mainRoomDB.specialDayDao().getSpecialDaysByCategory(category)
    }

    override fun getSpecialDaysCount(): Flow<Int> {
        return mainRoomDB.specialDayDao().getSpecialDaysCount()
    }

    // Dating Story operations
    override suspend fun insertDatingStory(story: DatingStory): Long {
        return mainRoomDB.datingStoryDao().insertDatingStory(story)
    }

    override suspend fun updateDatingStory(story: DatingStory) {
        mainRoomDB.datingStoryDao().updateDatingStory(story)
    }

    override suspend fun deleteDatingStory(story: DatingStory) {
        mainRoomDB.datingStoryDao().deleteDatingStory(story)
    }

    override fun getAllDatingStories(): Flow<List<DatingStory>> {
        return mainRoomDB.datingStoryDao().getAllDatingStories()
    }

    override suspend fun getDatingStoryById(id: Int): DatingStory? {
        return mainRoomDB.datingStoryDao().getDatingStoryById(id)
    }

    override fun getFavoriteDatingStories(): Flow<List<DatingStory>> {
        return mainRoomDB.datingStoryDao().getFavoriteDatingStories()
    }

    override fun getDatingStoriesCount(): Flow<Int> {
        return mainRoomDB.datingStoryDao().getDatingStoriesCount()
    }

    // Recommendation operations
    override suspend fun insertRecommendation(recommendation: Recommendation): Long {
        return mainRoomDB.recommendationDao().insertRecommendation(recommendation)
    }

    override suspend fun insertRecommendations(recommendations: List<Recommendation>) {
        mainRoomDB.recommendationDao().insertRecommendations(recommendations)
    }

    override suspend fun updateRecommendation(recommendation: Recommendation) {
        mainRoomDB.recommendationDao().updateRecommendation(recommendation)
    }

    override suspend fun deleteRecommendation(recommendation: Recommendation) {
        mainRoomDB.recommendationDao().deleteRecommendation(recommendation)
    }

    override fun getAllRecommendations(): Flow<List<Recommendation>> {
        return mainRoomDB.recommendationDao().getAllRecommendations()
    }

    override suspend fun getRecommendationById(id: Int): Recommendation? {
        return mainRoomDB.recommendationDao().getRecommendationById(id)
    }

    override fun getLikedRecommendations(): Flow<List<Recommendation>> {
        return mainRoomDB.recommendationDao().getLikedRecommendations()
    }

    override suspend fun updateRecommendationLike(id: Int, isLiked: Boolean) {
        mainRoomDB.recommendationDao().updateRecommendationLike(id, isLiked)
    }

    override suspend fun clearRecommendations() {
        mainRoomDB.recommendationDao().deleteAllRecommendations()
    }
}