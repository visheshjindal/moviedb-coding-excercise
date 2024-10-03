package com.vishesh.moviedetails.state

import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.MovieDetails

sealed interface MovieDetailsUiState {
    data object Idle : MovieDetailsUiState

    data object Loading : MovieDetailsUiState

    data class Error(
        val message: String,
    ) : MovieDetailsUiState

    data class Success(
        val movieDetails: MovieDetails,
        val castList: List<Cast>,
    ) : MovieDetailsUiState
}
