package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing user and partner profile information
 * This is a singleton table (only one row with id=1)
 */
@Entity(tableName = "user_profile")
data class User(
    @PrimaryKey
    val id: Int = 1, // Always 1 since we only have one couple
    val userName: String = "",
    val partnerName: String = "",
    val userPhotoUri: String? = null,
    val partnerPhotoUri: String? = null,
    val relationshipStartDate: Long? = null, // Timestamp in milliseconds
    val userPhotoResId: Int? = null, // For drawable resource IDs
    val partnerPhotoResId: Int? = null, // For drawable resource IDs
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
