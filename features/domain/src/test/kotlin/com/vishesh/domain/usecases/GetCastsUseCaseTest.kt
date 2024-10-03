package com.vishesh.domain.usecases

import com.vishesh.common.Result
import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.Movie
import com.vishesh.domain.repositories.MoviesRepository
import com.vishesh.domain.testdata.dummyCastList
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

class GetCastsUseCaseTest {
    private lateinit var getCastsUseCase: GetCastsUseCase
    private val testDispatcher = StandardTestDispatcher()
    private val moviesRepository: MoviesRepository = mockk()

    @BeforeEach
    fun setUp() {
        getCastsUseCase =
            GetCastsUseCase(
                repository = moviesRepository,
            )
    }

    @Test
    fun `invoke should return success with cast list when repository returns success`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getCasts(any()) } returns Result.Success(dummyCastList)
            // When
            val subject = getCastsUseCase(1)
            // Then
            expectThat(subject)
                .isA<Result.Success<List<Cast>>>()
                .and { get { data }.isEqualTo(dummyCastList) }
        }

    @Test
    fun `invoke should return error when repository returns error`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getCasts(any()) } returns
                Result.Error("Unknown error", IllegalStateException())
            // When
            val subject = getCastsUseCase(1)
            // Then
            expectThat(subject)
                .isA<Result.Error>()
                .and { get { message }.isEqualTo("Unknown error") }
        }

    @Test
    fun `invoke should return success with emptyList when repository returns success with emptyList`() =
        runTest(testDispatcher) {
            // Given
            coEvery { moviesRepository.getCasts(any()) } returns Result.Success(emptyList())
            // When
            val subject = getCastsUseCase(1)
            // Then
            expectThat(subject)
                .isA<Result.Success<List<Movie>>>()
                .and { get { data.size }.isEqualTo(0) }
        }

    @Test
    fun `invoke should call repository getCasts with given movie id`() =
        runTest(testDispatcher) {
            // Given
            val movieIdList = mutableListOf<Int>()
            coEvery { moviesRepository.getCasts(capture(movieIdList)) } returns
                Result.Success(
                    emptyList(),
                )
            // When
            getCastsUseCase(1)
            // Then
            coVerify { moviesRepository.getCasts(any()) }
            expectThat(movieIdList[0]).isEqualTo(1)
        }
}
