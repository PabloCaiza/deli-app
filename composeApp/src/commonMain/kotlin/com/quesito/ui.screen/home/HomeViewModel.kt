package com.quesito.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quesito.data.Movie
import com.quesito.data.MovieRepository
import com.quesito.data.MovieService
import com.quesito.data.RemoteMovie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    var state by mutableStateOf(UiState())
        private set


    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            movieRepository.movies.collect{
                if (it.isNotEmpty()){
                    state = UiState(loading = false, movies = it)
                }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}

