package com.quesito.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quesito.data.Movie

const val DATABASE_NAME = "movie.db "

interface DB{
    fun clearAllTables()
}

@Database(entities = [Movie::class], version = 1)
abstract  class MoviesDatabase:RoomDatabase(),DB {
    abstract fun moviesDao ():MovieDao
    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
}