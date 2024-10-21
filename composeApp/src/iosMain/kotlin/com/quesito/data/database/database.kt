package com.quesito.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<MoviesDatabase>{
    val dbFile = NSHomeDirectory()+ "/$DATABASE_NAME"
    return Room.databaseBuilder<MoviesDatabase>(name = dbFile, factory = {MoviesDatabase::class.instantiateImpl() } )
        .setDriver(BundledSQLiteDriver( ))
}