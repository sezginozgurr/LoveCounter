package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a couple's recommendationEntity/story for caching
 * Can be fetched from remote API and cached locally
 */
@Entity(tableName = "recommendationEntities")
data class RecommendationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val coupleName: String,
    val duration: String,
    val description: String,
    val photoUri: String? = null,
    val photoResId: Int? = null,
    val isLiked: Boolean = false,
    val likeCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)
