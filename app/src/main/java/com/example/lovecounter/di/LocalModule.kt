package com.example.lovecounter.di

import android.content.Context
import androidx.room.Room
import com.example.lovecounter.data.source.local.DatingStoryDao
import com.example.lovecounter.data.source.local.MainRoomDB
import com.example.lovecounter.data.source.local.MemoryDao
import com.example.lovecounter.data.source.local.RecommendationDao
import com.example.lovecounter.data.source.local.SpecialDayDao
import com.example.lovecounter.data.source.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MainRoomDB {
        return Room.databaseBuilder(
            context,
            MainRoomDB::class.java,
            "main_database"
        )
            .fallbackToDestructiveMigration() // For development - remove in production
            .build()
    }

    @Provides
    fun provideUserDao(database: MainRoomDB): UserDao = database.userDao()

    @Provides
    fun provideMemoryDao(database: MainRoomDB): MemoryDao = database.memoryDao()

    @Provides
    fun provideSpecialDayDao(database: MainRoomDB): SpecialDayDao = database.specialDayDao()

    @Provides
    fun provideDatingStoryDao(database: MainRoomDB): DatingStoryDao = database.datingStoryDao()

    @Provides
    fun provideRecommendationDao(database: MainRoomDB): RecommendationDao = database.recommendationDao()
}