package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing user and partner profile information
 * This is a singleton table (only one row with id=1)
 */
@Entity(tableName = "user_profile")
data class UserEntity(
    @PrimaryKey
    val id: Int = 1,
    val userName: String = "",
    val partnerName: String = "",
    val relationshipStartDate: Long? = null,
    val userPhotoResId: Int? = null,
    val partnerPhotoResId: Int? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)
