package com.example.lovecounter.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lovecounter.data.model.MainEntityModel
import com.example.lovecounter.data.model.Memory

@Database(entities = [MainEntityModel::class, Memory::class], version = 1, exportSchema = false)
@TypeConverters(UriListConverter::class)
abstract class MainRoomDB : RoomDatabase() {
    abstract fun mainDao(): MainDao
}