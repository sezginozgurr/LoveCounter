package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lovecounter.data.source.local.UriListConverter

/**
 * Entity representing a memoryEntity/moment in the relationship
 */
@Entity(tableName = "memories")
@TypeConverters(UriListConverter::class)
data class MemoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val subtitle: String,
    val description: String? = null,
    val photoUris: List<String> = emptyList(),
    val dateTimestamp: Long = System.currentTimeMillis(),
    val location: String? = null,
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)