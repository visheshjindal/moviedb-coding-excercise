package com.vishesh.data.repositories

import com.vishesh.common.CoroutineDispatcherProvider
import com.vishesh.common.Result
import com.vishesh.common.getOr
import com.vishesh.data.mappers.toCast
import com.vishesh.data.mappers.toMovie
import com.vishesh.data.mappers.toMovieDetails
import com.vishesh.data.services.MoviesService
import com.vishesh.domain.repositories.MoviesRepository
import com.vishesh.network.utils.apiCallToResult
import javax.inject.Inject

class MoviesRepositoryImpl
    @Inject
    constructor(
        private val moviesService: MoviesService,
        private val dispatcherProvider: CoroutineDispatcherProvider,
    ) : MoviesRepository {
        override suspend fun getMovies() =
            apiCallToResult(
                apiCall = { moviesService.getMovies() },
                transform = { moviesResponse ->
                    val genresMap = getGenres().getOr(emptyList()).associateBy { it.id }
                    Result.Success(
                        moviesResponse.results.map {
                            val genreNames =
                                it.genreIds.mapNotNull { genreId -> genresMap[genreId]?.name }
                            it.toMovie(genreNames)
                        },
                    )
                },
                coroutineDispatcher = dispatcherProvider,
            )

        override suspend fun getMovieDetails(movieId: Int) =
            apiCallToResult(
                apiCall = { moviesService.getMovieDetails(movieId) },
                transform = { movieDetailsResponse -> Result.Success(movieDetailsResponse.toMovieDetails()) },
                coroutineDispatcher = dispatcherProvider,
            )

        override suspend fun getCasts(movieId: Int) =
            apiCallToResult(
                apiCall = { moviesService.getCastList(movieId) },
                transform = { castsResponse -> Result.Success(castsResponse.cast.map { it.toCast() }) },
                coroutineDispatcher = dispatcherProvider,
            )

        private suspend fun getGenres() =
            apiCallToResult(
                apiCall = { moviesService.getGenres() },
                transform = { genresResponse -> Result.Success(genresResponse.genres) },
                coroutineDispatcher = dispatcherProvider,
            )
    }
