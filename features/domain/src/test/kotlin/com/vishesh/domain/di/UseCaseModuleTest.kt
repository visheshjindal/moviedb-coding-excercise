package com.vishesh.domain.di

import com.vishesh.domain.repositories.MoviesRepository
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isNotNull

class UseCaseModuleTest {
    private val repository: MoviesRepository = mockk()
    private val useCaseModule = UseCaseModule()

    @Test
    fun `verify provideGetCastsUseCase returns a non-null GetCastsUseCase instance`() {
        val getCastsUseCase = useCaseModule.provideGetCastsUseCase(repository)
        expectThat(getCastsUseCase).isNotNull()
    }

    @Test
    fun `verify provideGetMovieDetailsUseCase returns a non-null GetMovieDetailsUseCase instance`() {
        val getMovieDetailsUseCase =
            useCaseModule.provideGetMovieDetailsUseCase(repository)
        expectThat(getMovieDetailsUseCase).isNotNull()
    }

    @Test
    fun `verify provideGetMoviesUseCase returns a non-null GetMoviesUseCase instance`() {
        val getMoviesUseCase = useCaseModule.provideGetMoviesUseCase(repository)
        expectThat(getMoviesUseCase).isNotNull()
    }
}
