package com.example.lovecounter.data.model

/**
 * Data model representing a couple's recommendation/story
 */
data class Recommendation(
    val id: Int,
    val coupleName: String,
    val duration: String,
    val description: String,
    val photoResId: Int,
    val isLiked: Boolean = false,
)
