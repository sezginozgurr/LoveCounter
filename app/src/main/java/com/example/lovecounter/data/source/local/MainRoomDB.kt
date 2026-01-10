package com.example.lovecounter.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lovecounter.data.model.DatingStory
import com.example.lovecounter.data.model.Memory
import com.example.lovecounter.data.model.Recommendation
import com.example.lovecounter.data.model.SpecialDay
import com.example.lovecounter.data.model.User

/**
 * Main Room database for Love Counter app
 * Contains all entities and provides access to DAOs
 */
@Database(
    entities = [
        User::class,
        Memory::class,
        SpecialDay::class,
        DatingStory::class,
        Recommendation::class
    ],
    version = 2,
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