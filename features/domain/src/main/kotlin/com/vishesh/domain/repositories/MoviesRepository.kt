package com.vishesh.domain.repositories

import com.vishesh.common.Result
import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.Movie
import com.vishesh.domain.entities.MovieDetails

interface MoviesRepository {
    suspend fun getMovies(): Result<List<Movie>>

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetails>

    suspend fun getCasts(movieId: Int): Result<List<Cast>>
}
