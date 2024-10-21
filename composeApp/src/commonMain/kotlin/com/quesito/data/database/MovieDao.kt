package com.quesito.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.quesito.data.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun fetchPopularMovies():Flow<List<Movie>>
    @Query("SELECT * FROM Movie WHERE id= :id")
    fun fetchMovieById(id:Int):Flow<Movie?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movies: List<Movie>)

}