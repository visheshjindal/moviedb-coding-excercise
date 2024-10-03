package com.vishesh.movielist.presentation.viewmodel

import app.cash.turbine.test
import com.vishesh.common.Result
import com.vishesh.domain.entities.Movie
import com.vishesh.domain.usecases.GetMoviesUseCase
import com.vishesh.movielist.intent.MovieListIntent
import com.vishesh.movielist.state.MovieListUiState
import com.vishesh.movielist.viewmodel.MovieListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {
    private val getMoviesUseCase: GetMoviesUseCase = mockk()
    private lateinit var viewModel: MovieListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieListViewModel(getMoviesUseCase)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `subscribe ui state success should update ui state with movies`() =
        runTest {
            // Given
            coEvery { getMoviesUseCase() } returns Result.Success(mockMovies)

            viewModel.movieListUiState.test {
                expectThat(awaitItem())
                    .isA<MovieListUiState.Idle>()
                expectThat(awaitItem())
                    .isA<MovieListUiState.Success>()
                    .and { get { movies }.isEqualTo(mockMovies) }
            }
            coVerify(exactly = 1) { getMoviesUseCase() }
        }

    @Test
    fun `FetchMovies success should update ui state with movies`() =
        runTest {
            // Given
            coEvery { getMoviesUseCase() } returns Result.Success(emptyList())
            viewModel.movieListUiState.test {
                expectThat(awaitItem()).isA<MovieListUiState.Idle>()
                expectThat(awaitItem())
                    .isA<MovieListUiState.Success>()
                    .and { get { movies }.isEmpty() }

                coEvery { getMoviesUseCase() } returns Result.Success(mockMovies)
                // When
                viewModel.processIntent(MovieListIntent.FetchMovies)
                // Then
                expectThat(awaitItem())
                    .isA<MovieListUiState.Success>()
                    .and { get { movies }.isEqualTo(mockMovies) }
                ensureAllEventsConsumed()
            }
            coVerify(exactly = 2) { getMoviesUseCase() }
        }

    @Test
    fun `subscribe error should update ui state with error message`() =
        runTest {
            // Given
            coEvery { getMoviesUseCase() } returns
                Result.Error(
                    "Unexpected Error",
                    IllegalStateException(),
                )
            // When
            viewModel.movieListUiState.test {
                // Then
                expectThat(awaitItem()).isA<MovieListUiState.Idle>()
                expectThat(awaitItem())
                    .isA<MovieListUiState.Error>()
                    .and { get { message }.isEqualTo("Unexpected Error") }
                ensureAllEventsConsumed()
            }
            coVerify(exactly = 1) { getMoviesUseCase() }
        }

    @Test
    fun `NavigateToMovieDetails should not do anything`() =
        runTest {
            // When
            viewModel.processIntent(MovieListIntent.NavigateToMovieDetails(1))
            // Then
            coVerify(exactly = 0) { getMoviesUseCase() }
        }

    private companion object {
        private val mockMovies: List<Movie> =
            listOf(
                Movie(
                    id = 1,
                    title = "Movie 1",
                    overview = "Overview 1",
                    posterPath = "https://image.tmdb.org/t/p/w342/poster1.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w780/backdrop1.jpg",
                    releaseDate = "2021-01-01",
                    voteAverage = 7.5,
                    genreNames = listOf("Genre 1", "Genre 2"),
                ),
                Movie(
                    id = 2,
                    title = "Movie 2",
                    overview = "Overview 2",
                    posterPath = "https://image.tmdb.org/t/p/w342/poster2.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w780/backdrop2.jpg",
                    releaseDate = "2021-01-02",
                    voteAverage = 8.5,
                    genreNames = listOf("Genre 2", "Genre 3"),
                ),
                Movie(
                    id = 3,
                    title = "Movie 3",
                    overview = "Overview 3",
                    posterPath = "https://image.tmdb.org/t/p/w342/poster3.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w780/backdrop3.jpg",
                    releaseDate = "2021-01-03",
                    voteAverage = 9.5,
                    genreNames = listOf("Genre 3", "Genre 4"),
                ),
            )
    }
}
