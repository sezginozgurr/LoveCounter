package com.example.lovecounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing special days like anniversaries, birthdays, etc.
 */
@Entity(tableName = "special_days")
data class SpecialDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val dateTimestamp: Long, // Timestamp in milliseconds
    val timeInMillis: Long? = null, // Time component if user wants to set specific time
    val category: String, // ANNIVERSARY, MEETING_ANNIVERSARY, BIRTHDAY, OTHER
    val iconResId: Int? = null, // Icon resource ID
    val imageUri: String? = null, // Optional image URI
    val isRecurring: Boolean = true, // Whether it repeats annually
    val reminderEnabled: Boolean = false,
    val reminderDaysBefore: Int = 1, // Days before to remind
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
