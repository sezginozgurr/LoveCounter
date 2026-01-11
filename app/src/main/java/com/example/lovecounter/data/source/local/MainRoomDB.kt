package com.example.lovecounter.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lovecounter.data.model.DatingStoryEntity
import com.example.lovecounter.data.model.MemoryEntity
import com.example.lovecounter.data.model.RecommendationEntity
import com.example.lovecounter.data.model.SpecialDayEntity
import com.example.lovecounter.data.model.UserEntity

/**
 * Main Room database for Love Counter app
 * Contains all entities and provides access to DAOs
 */
@Database(
    entities = [
        UserEntity::class,
        MemoryEntity::class,
        SpecialDayEntity::class,
        DatingStoryEntity::class,
        RecommendationEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(UriListConverter::class, Converters::class)
abstract class MainRoomDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun memoryDao(): MemoryDao
    abstract fun specialDayDao(): SpecialDayDao
    abstract fun datingStoryDao(): DatingStoryDao
    abstract fun recommendationDao(): RecommendationDao
}