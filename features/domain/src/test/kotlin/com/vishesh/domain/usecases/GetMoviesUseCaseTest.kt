package com.vishesh.domain.usecases

import com.vishesh.common.Result
import com.vishesh.domain.entities.Movie
import com.vishesh.domain.repositories.MoviesRepository
import com.vishesh.domain.testdata.dummyMovieListData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class GetMoviesUseCaseTest {
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private val moviesRepository: MoviesRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        getMoviesUseCase =
            GetMoviesUseCase(
                repository = moviesRepository,
            )
    }

    @Test
    fun `invoke should return success with data when repository returns success`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getMovies() } returns Result.Success(dummyMovieListData)
            // When
            val subject = getMoviesUseCase()
            // Then
            expectThat(subject)
                .isA<Result.Success<List<Movie>>>()
                .and { get { data }.isEqualTo(dummyMovieListData) }
        }

    @Test
    fun `invoke should return success with emptyList when repository returns success with emptyList`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getMovies() } returns Result.Success(emptyList())
            // When
            val subject = getMoviesUseCase()
            // Then
            expectThat(subject)
                .isA<Result.Success<List<Movie>>>()
                .and { get { data.size }.isEqualTo(0) }
        }

    @Test
    fun `invoke should return error when repository returns error`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getMovies() } returns
                Result.Error(
                    "Unknown error",
                    IllegalStateException(),
                )
            // When
            val subject = getMoviesUseCase()
            // Then
            expectThat(subject)
                .isA<Result.Error>()
                .and { get { message }.isEqualTo("Unknown error") }
        }

    @Test
    fun `invoke should call repository getMovies`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getMovies() } returns Result.Success(emptyList())
            // When
            getMoviesUseCase()
            // Then
            coVerify { moviesRepository.getMovies() }
        }
}
