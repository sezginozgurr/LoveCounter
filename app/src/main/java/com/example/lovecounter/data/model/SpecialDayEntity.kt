package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "special_days")
data class SpecialDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val dateTimestamp: Long,
    val timeInMillis: Long? = null,
    val category: String,
    val iconResId: Int? = null,
    val imageUri: String? = null,
    val isRecurring: Boolean = true,
    val reminderEnabled: Boolean = false,
    val reminderDaysBefore: Int = 1,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)
