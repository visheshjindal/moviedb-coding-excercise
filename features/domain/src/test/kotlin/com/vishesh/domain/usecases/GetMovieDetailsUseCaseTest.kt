package com.vishesh.domain.usecases

import com.vishesh.common.Result
import com.vishesh.domain.entities.MovieDetails
import com.vishesh.domain.repositories.MoviesRepository
import com.vishesh.domain.testdata.dummyMovieDetails
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

class GetMovieDetailsUseCaseTest {
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private val moviesRepository: MoviesRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        getMovieDetailsUseCase =
            GetMovieDetailsUseCase(repository = moviesRepository)
    }

    @Test
    fun `invoke should return success with data when repository returns success`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getMovieDetails(any()) } returns
                Result.Success(
                    dummyMovieDetails,
                )
            // When
            val subject = getMovieDetailsUseCase(1)
            // Then
            expectThat(subject)
                .isA<Result.Success<MovieDetails>>()
                .get { data }
                .isEqualTo(dummyMovieDetails)
        }

    @Test
    fun `invoke should return error when repository returns error`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getMovieDetails(any()) } returns
                Result.Error(
                    "Unknown error",
                    IllegalStateException(),
                )
            // When
            val subject = getMovieDetailsUseCase(1)
            // Then
            expectThat(subject)
                .isA<Result.Error>()
                .and { get { message }.isEqualTo("Unknown error") }
        }

    @Test
    fun `invoke should call repository getMovieDetails with given movie id`() =
        runTest(testDispatcher) {
            // Given
            val movieIdList = mutableListOf<Int>()
            coEvery { moviesRepository.getMovieDetails(capture(movieIdList)) } returns
                Result.Success(
                    dummyMovieDetails,
                )
            // When
            getMovieDetailsUseCase(1)
            // Then
            coVerify { moviesRepository.getMovieDetails(any()) }
            expectThat(movieIdList[0]).isEqualTo(1)
        }
}
