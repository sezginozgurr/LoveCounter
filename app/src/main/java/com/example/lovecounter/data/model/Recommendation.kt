package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a couple's recommendation/story for caching
 * Can be fetched from remote API and cached locally
 */
@Entity(tableName = "recommendations")
data class Recommendation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val coupleName: String,
    val duration: String, // E.g., "5 yıl birlikte"
    val description: String,
    val photoUri: String? = null, // Photo URI for remote images
    val photoResId: Int? = null, // Photo resource ID for local images
    val isLiked: Boolean = false,
    val likeCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
