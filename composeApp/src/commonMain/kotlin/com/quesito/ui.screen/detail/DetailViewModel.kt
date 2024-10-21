package com.quesito.ui.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quesito.data.Movie
import com.quesito.data.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int, private val repository: MovieRepository) : ViewModel() {
    var state by mutableStateOf(UiState())
    private set
    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            repository.fetchMovieById(id).collect{
                it?.let {state= UiState(loading = false, movie = it)}
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null
    )

}