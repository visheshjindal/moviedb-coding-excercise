package com.vishesh.domain.usecases

import com.vishesh.domain.repositories.MoviesRepository
import javax.inject.Inject

class GetCastsUseCase
    @Inject
    constructor(
        private val repository: MoviesRepository,
    ) {
        suspend operator fun invoke(movieId: Int) = repository.getCasts(movieId)
    }
