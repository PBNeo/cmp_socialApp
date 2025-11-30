package com.neosoft.localStorage.repository

import com.neosoft.localStorage.dao.UserInfoDao
import com.neosoft.localStorage.database.AppDatabase
import com.neosoft.localStorage.entity.UserInfo

class UserRepository(private val appDatabase: AppDatabase) {
    suspend fun saveUser(user: UserInfo) = appDatabase.userInfoDao().insertUser(user)
    suspend fun getUser(email: String): UserInfo? = appDatabase.userInfoDao().getUserByEmail(email)
    suspend fun deleteUser(email: String) = appDatabase.userInfoDao().deleteUserByEmail(email)
}
