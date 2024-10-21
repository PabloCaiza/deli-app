package com.quesito.data

import com.quesito.data.database.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class MovieRepository(private val movieService: MovieService,
                      private val movieDao: MovieDao,
                      private val regionRepository: RegionRepository) {

    val movies:Flow<List<Movie>> = movieDao.fetchPopularMovies().onEach { movies ->
        if (movies.isEmpty()) {
            val popularMovies =
                movieService.fetchPopularMovies(regionRepository.fetchRegion())
                    .results.map { it.toDomainMovie() };
            movieDao.save(popularMovies)
        }
    }

    suspend fun fetchMovieById(id: Int): Flow<Movie?> =
        movieDao.fetchMovieById(id).onEach { movie ->
            if (movie == null) {
                val remoteMovie = movieService.fetchMovieById(id).toDomainMovie()
                movieDao.save(listOf( remoteMovie))
            }
        }

    private fun RemoteMovie.toDomainMovie() = Movie(
        id = id,
        title = title,
        poster = "https://image.tmdb.org/t/p/w500$posterPath",
        backdrop = backdropPath?.let { "https://image.tmdb.org/t/p/w780$it" },
        originalLanguage = originalLanguage,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        popularity = popularity,
        overview = overview,
        originalTitle = originalTitle
    )
}
