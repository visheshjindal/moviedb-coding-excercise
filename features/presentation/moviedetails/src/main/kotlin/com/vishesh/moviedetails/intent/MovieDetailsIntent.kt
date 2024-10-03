package com.vishesh.moviedetails.intent

sealed interface MovieDetailsIntent {
    data object FetchMoviesDetail : MovieDetailsIntent

    data object OnBackNavigation : MovieDetailsIntent
}
