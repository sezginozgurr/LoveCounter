package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.SpecialDay
import kotlinx.coroutines.flow.Flow

/**
 * DAO for SpecialDay operations
 */
@Dao
interface SpecialDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecialDay(specialDay: SpecialDay): Long

    @Update
    suspend fun updateSpecialDay(specialDay: SpecialDay)

    @Delete
    suspend fun deleteSpecialDay(specialDay: SpecialDay)

    @Query("SELECT * FROM special_days ORDER BY dateTimestamp ASC")
    fun getAllSpecialDays(): Flow<List<SpecialDay>>

    @Query("SELECT * FROM special_days WHERE id = :id")
    suspend fun getSpecialDayById(id: Int): SpecialDay?

    @Query("SELECT * FROM special_days WHERE category = :category ORDER BY dateTimestamp ASC")
    fun getSpecialDaysByCategory(category: String): Flow<List<SpecialDay>>

    @Query("SELECT * FROM special_days WHERE isRecurring = 1 ORDER BY dateTimestamp ASC")
    fun getRecurringSpecialDays(): Flow<List<SpecialDay>>

    @Query("SELECT * FROM special_days WHERE reminderEnabled = 1 ORDER BY dateTimestamp ASC")
    fun getSpecialDaysWithReminders(): Flow<List<SpecialDay>>

    @Query("DELETE FROM special_days WHERE id = :id")
    suspend fun deleteSpecialDayById(id: Int)

    @Query("SELECT COUNT(*) FROM special_days")
    fun getSpecialDaysCount(): Flow<Int>
}
