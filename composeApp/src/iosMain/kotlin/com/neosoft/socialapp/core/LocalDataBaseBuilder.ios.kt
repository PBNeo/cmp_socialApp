package com.neosoft.socialapp.core

import androidx.room.RoomDatabase
import com.neosoft.localStorage.database.AppDatabase
import com.neosoft.localStorage.database.buildIosDatabaseBuilder


actual object LocalDataBaseBuilder {
    actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        return buildIosDatabaseBuilder()
    }
}