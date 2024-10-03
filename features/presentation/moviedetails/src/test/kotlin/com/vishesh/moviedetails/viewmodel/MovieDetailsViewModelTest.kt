package com.vishesh.moviedetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.vishesh.common.Result
import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.MovieDetails
import com.vishesh.domain.usecases.GetCastsUseCase
import com.vishesh.domain.usecases.GetMovieDetailsUseCase
import com.vishesh.moviedetails.intent.MovieDetailsIntent
import com.vishesh.moviedetails.state.MovieDetailsUiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isA
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {
    private val movieIdKey = "movieId"
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    private val getCreditsUseCase: GetCastsUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    private fun createViewModel(movieId: Int? = 123): MovieDetailsViewModel {
        val savedStateHandle = SavedStateHandle(mapOf(movieIdKey to movieId))
        return MovieDetailsViewModel(
            getMovieDetailsUseCase,
            getCreditsUseCase,
            savedStateHandle,
        )
    }

    @Test
    fun `uiState should emit error when savedState does not have movieId`() =
        runTest {
            // Given
            val viewModel = createViewModel(null)

            launch {
                // When
                viewModel.movieDetailsUiState.test {
                    // Then
                    expectThat(awaitItem())
                        .isA<MovieDetailsUiState.Idle>()
                    expectThat(awaitItem())
                        .isA<MovieDetailsUiState.Error>()
                        .and { get { message }.isEqualTo("Malformed data.") }
                    cancelAndIgnoreRemainingEvents()
                }
            }
        }

    @Test
    fun `uiState should emit Success state when movieId is present`() =
        runTest {
            // Given
            val viewModel = createViewModel()
            coEvery { getMovieDetailsUseCase(any()) } returns Result.Success(movieDetails)
            coEvery { getCreditsUseCase(any()) } returns Result.Success(castList)
            // When
            viewModel.movieDetailsUiState.test {
                expectThat(awaitItem()).isA<MovieDetailsUiState.Idle>()
                expectThat(awaitItem())
                    .isA<MovieDetailsUiState.Success>()
                    .and {
                        get { movieDetails }.isEqualTo(movieDetails)
                        get { castList }.contains(castList)
                    }
                cancelAndIgnoreRemainingEvents()
            }
            coVerifyAll {
                getMovieDetailsUseCase(any())
                getCreditsUseCase(any())
            }
        }

    @Test
    fun `uiState should emit Error state when data fetching fails`() =
        runTest {
            // Given
            val movieId = 123
            val viewModel = createViewModel()
            coEvery { getMovieDetailsUseCase(movieId) } returns
                Result.Error(
                    "Error",
                    IllegalStateException(),
                )
            coEvery { getCreditsUseCase(movieId) } returns Result.Success(emptyList())
            // When
            viewModel.processIntent(MovieDetailsIntent.FetchMoviesDetail)
            // Then
            viewModel.movieDetailsUiState.test {
                expectThat(awaitItem())
                    .isA<MovieDetailsUiState.Idle>()
                expectThat(awaitItem())
                    .isA<MovieDetailsUiState.Error>()
                    .and { get { message }.isEqualTo("Error") }
                ensureAllEventsConsumed()
            }
            coVerify(exactly = 0) { getCreditsUseCase(movieId) }
        }

    @Test
    fun `uiState should emit Success with empty cast state when getMovieDetails success but getCredits fails`() =
        runTest {
            // Given
            val movieId = 123
            val viewModel = createViewModel()
            coEvery { getMovieDetailsUseCase(movieId) } returns Result.Success(movieDetails)
            coEvery { getCreditsUseCase(movieId) } returns
                Result.Error(
                    "Error",
                    IllegalStateException(),
                )
            // When
            viewModel.processIntent(MovieDetailsIntent.FetchMoviesDetail)
            // Then
            viewModel.movieDetailsUiState.test {
                expectThat(awaitItem())
                    .isA<MovieDetailsUiState.Idle>()
                expectThat(awaitItem())
                    .isA<MovieDetailsUiState.Success>()
                    .and { get { movieDetails }.isEqualTo(movieDetails) }
                ensureAllEventsConsumed()
            }
        }

    @Test
    fun `OnBackNavigation should do not change the ui state`() =
        runTest {
            // Given
            val viewModel = createViewModel()
            // When
            viewModel.processIntent(MovieDetailsIntent.OnBackNavigation)
            // Then
            expectThat(viewModel.movieDetailsUiState.value)
                .isA<MovieDetailsUiState.Idle>()
        }

    private companion object MockData {
        val movieDetails =
            MovieDetails(
                id = 123,
                title = "Movie 1",
                overview = "Overview",
                backdropPath = "backdrop.jpg",
                releaseDate = "2021-01-01",
                voteAverage = 7.5,
                runtime = 120,
                genreNames = listOf("Action", "Drama"),
                tagline = "Tagline",
                status = "Released",
            )
        val castList =
            listOf(
                Cast(
                    id = 1,
                    name = "Actor 1",
                    character = "Character 1",
                    profilePath = "profile.jpg",
                ),
                Cast(
                    id = 2,
                    name = "Actor 2",
                    character = "Character 2",
                    profilePath = "profile.jpg",
                ),
            )
    }
}
