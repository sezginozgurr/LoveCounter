package com.example.lovecounter.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lovecounter.data.model.User
import kotlinx.coroutines.flow.Flow

/**
 * DAO for User/Profile operations
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUser(): Flow<User?>

    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getUserOnce(): User?

    @Query("DELETE FROM user_profile")
    suspend fun deleteUser()
}
