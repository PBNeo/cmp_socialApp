package com.neosoft.socialapp.core

import androidx.room.RoomDatabase
import com.neosoft.localStorage.database.AppDatabase
import com.neosoft.localStorage.database.buildDatabaseBuilder
import com.neosoft.socialapp.MainActivity.Companion.appContext


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object LocalDataBaseBuilder {
    actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        return buildDatabaseBuilder(appContext);
    }
    }


