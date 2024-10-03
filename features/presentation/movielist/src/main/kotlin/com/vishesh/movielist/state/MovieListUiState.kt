package com.vishesh.movielist.state

import com.vishesh.domain.entities.Movie

sealed interface MovieListUiState {
    data object Idle : MovieListUiState

    data object Loading : MovieListUiState

    data class Success(
        val movies: List<Movie>,
    ) : MovieListUiState

    data class Error(
        val message: String,
    ) : MovieListUiState
}
