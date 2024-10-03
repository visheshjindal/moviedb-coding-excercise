package com.vishesh.data.services

import com.vishesh.data.models.CreditsResponse
import com.vishesh.data.models.GenresResponse
import com.vishesh.data.models.MovieDetailsResponse
import com.vishesh.data.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    @GET("movie/top_rated?language=en-US&page=1")
    suspend fun getMovies(): Response<MoviesResponse>

    @GET("genre/movie/list?language=en-US")
    suspend fun getGenres(): Response<GenresResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCastList(
        @Path("movie_id") movieId: Int,
    ): Response<CreditsResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): Response<MovieDetailsResponse>
}
