package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a couple's dating story or shared experience
 */
@Entity(tableName = "dating_stories")
data class DatingStory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val photoUri: String? = null, // Photo URI for user uploaded images
    val photoResId: Int? = null, // Photo resource ID for default images
    val dateTimestamp: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
