package com.neosoft.localStorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neosoft.localStorage.dao.UserInfoDao
import com.neosoft.localStorage.entity.UserInfo
@Database(entities = [UserInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
}
