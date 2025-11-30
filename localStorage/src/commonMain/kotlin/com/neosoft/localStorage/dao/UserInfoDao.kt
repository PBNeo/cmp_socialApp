package com.neosoft.localStorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neosoft.localStorage.entity.UserInfo

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM UserInfo WHERE emailId = :email")
    suspend fun getUserByEmail(email: String): UserInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserInfo)

    @Query("DELETE FROM UserInfo WHERE emailId = :email")
    suspend fun deleteUserByEmail(email: String)
}
