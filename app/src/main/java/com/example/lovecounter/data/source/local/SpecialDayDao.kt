package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.SpecialDayEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for SpecialDayEntity operations
 */
@Dao
interface SpecialDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecialDay(specialDayEntity: SpecialDayEntity): Long

    @Update
    suspend fun updateSpecialDay(specialDayEntity: SpecialDayEntity)

    @Delete
    suspend fun deleteSpecialDay(specialDayEntity: SpecialDayEntity)

    @Query("SELECT * FROM special_days ORDER BY dateTimestamp ASC")
    fun getAllSpecialDays(): Flow<List<SpecialDayEntity>>

    @Query("SELECT * FROM special_days WHERE id = :id")
    suspend fun getSpecialDayById(id: Int): SpecialDayEntity?

    @Query("SELECT * FROM special_days WHERE category = :category ORDER BY dateTimestamp ASC")
    fun getSpecialDaysByCategory(category: String): Flow<List<SpecialDayEntity>>

    @Query("SELECT * FROM special_days WHERE isRecurring = 1 ORDER BY dateTimestamp ASC")
    fun getRecurringSpecialDays(): Flow<List<SpecialDayEntity>>

    @Query("SELECT * FROM special_days WHERE reminderEnabled = 1 ORDER BY dateTimestamp ASC")
    fun getSpecialDaysWithReminders(): Flow<List<SpecialDayEntity>>

    @Query("DELETE FROM special_days WHERE id = :id")
    suspend fun deleteSpecialDayById(id: Int)

    @Query("SELECT COUNT(*) FROM special_days")
    fun getSpecialDaysCount(): Flow<Int>
}
