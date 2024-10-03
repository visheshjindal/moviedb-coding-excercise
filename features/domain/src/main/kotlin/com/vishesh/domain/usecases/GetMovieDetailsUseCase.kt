package com.vishesh.domain.usecases

import com.vishesh.domain.repositories.MoviesRepository
import javax.inject.Inject

class GetMovieDetailsUseCase
    @Inject
    constructor(
        private val repository: MoviesRepository,
    ) {
        suspend operator fun invoke(movieId: Int) = repository.getMovieDetails(movieId)
    }
