package com.vishesh.navigation

sealed class NavRoutes(
    val route: String,
) {
    data object MovieList : NavRoutes("movieList")

    data object MovieDetails : NavRoutes("movieDetail/{movieId}") {
        const val MOVIE_ID = "movieId"

        fun createRoute(movieId: Int) = "movieDetail/$movieId"
    }
}
