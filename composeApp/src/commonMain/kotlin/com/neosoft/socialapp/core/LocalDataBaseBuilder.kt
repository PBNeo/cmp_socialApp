package com.neosoft.socialapp.core

import androidx.room.RoomDatabase
import com.neosoft.localStorage.database.AppDatabase

expect object  LocalDataBaseBuilder {
    fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
}