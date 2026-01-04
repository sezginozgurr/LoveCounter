package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lovecounter.data.source.local.UriListConverter

@Entity(tableName = "memories")
@TypeConverters(UriListConverter::class)
data class Memory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val subtitle: String,
    val photoUris: List<String>,
)