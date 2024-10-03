package com.vishesh.movielist.intent

sealed interface MovieListIntent {
    data object FetchMovies : MovieListIntent

    data class NavigateToMovieDetails(
        val movieId: Int,
    ) : MovieListIntent
}
