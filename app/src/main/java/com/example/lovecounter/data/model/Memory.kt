package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lovecounter.data.source.local.UriListConverter

/**
 * Entity representing a memory/moment in the relationship
 */
@Entity(tableName = "memories")
@TypeConverters(UriListConverter::class)
data class Memory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val subtitle: String,
    val description: String? = null,
    val photoUris: List<String> = emptyList(),
    val dateTimestamp: Long = System.currentTimeMillis(), // When the memory occurred
    val location: String? = null, // Optional location
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)