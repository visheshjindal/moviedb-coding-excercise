package com.vishesh.data.repositories

import com.vishesh.common.Result
import com.vishesh.data.models.CreditsResponse
import com.vishesh.data.models.MovieDetailsResponse
import com.vishesh.data.models.MoviesResponse
import com.vishesh.data.services.MoviesService
import com.vishesh.data.testdata.MockData
import com.vishesh.data.utils.TestCoroutineDispatcher
import com.vishesh.data.utils.createErrorResponse
import com.vishesh.domain.entities.Cast
import com.vishesh.domain.entities.Movie
import com.vishesh.domain.entities.MovieDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesRepositoryImplTest {
    private val moviesService: MoviesService = mockk(relaxed = true)

    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl
    private val testDispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher.io)
        moviesRepositoryImpl = MoviesRepositoryImpl(moviesService, testDispatcher)
    }

    @AfterEach
    fun teatDown() {
        Dispatchers.resetMain()
        testDispatcher.io.cancel()
    }

    @Test
    fun `getMovies() should return Success with mapped movies when API call is successful`() =
        runTest {
            // Given
            val movieResponse = MockData.movieResponse
            coEvery { moviesService.getMovies() } returns movieResponse
            coEvery { moviesService.getGenres() } returns MockData.genresResponse

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getMovies() }
            expectThat(subject)
                .isA<Result.Success<List<Movie>>>()
                .and { get { data }.isEqualTo(MockData.movies) }
        }

    @Test
    fun `getMovies() should return Error when API call is unsuccessful`() =
        runTest {
            // Given
            val exception = Exception("Something went wrong")
            coEvery { moviesService.getMovies() } throws exception

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getMovies() }
            expectThat(subject)
                .isA<Result.Error>()
                .and { get { message }.isEqualTo("Something went wrong") }
        }

    @Test
    fun `getMovies() should return 401 Http Error response from API call`() =
        runTest {
            val errorResponse = createErrorResponse<MoviesResponse>(401, "Unauthorized Access")
            coEvery { moviesService.getMovies() } returns errorResponse

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getMovies() }
            expectThat(subject)
                .isA<Result.Error>()
                .and { get { message }.isEqualTo("HTTP Error: 401 : Unauthorized Access") }
        }

    @Test
    fun `getMovieDetails() should return Success with mapped movie details when API call is successful`() =
        runTest {
            // Given
            val movieId = 1
            val movieDetailsResponse = MockData.movieDetailsResponse
            coEvery { moviesService.getMovieDetails(movieId) } returns movieDetailsResponse

            // When
            val subject = moviesRepositoryImpl.getMovieDetails(movieId)

            // Then
            coVerify { moviesService.getMovieDetails(movieId) }
            expectThat(subject)
                .isA<Result.Success<MovieDetails>>()
                .and { get { data }.isEqualTo(MockData.movieDetails) }
        }

    @Test
    fun `getMovieDetails() should return 404 Http Error response from API call`() =
        runTest {
            val errorResponse =
                createErrorResponse<MovieDetailsResponse>(404, "Not found")
            coEvery { moviesService.getMovieDetails(any()) } returns errorResponse

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getMovies() }
            expectThat(subject).isA<Result.Error>()
        }

    @Test
    fun `getMovieDetails() should return Error when API call is unsuccessful`() =
        runTest {
            // Given
            val movieId = 1
            val exception = Exception("Something went wrong")
            coEvery { moviesService.getMovieDetails(movieId) } throws exception

            // When
            val subject = moviesRepositoryImpl.getMovieDetails(movieId)

            // Then
            coVerify { moviesService.getMovieDetails(movieId) }
            expectThat(subject)
                .isA<Result.Error>()
                .and { get { message }.isEqualTo("Something went wrong") }
        }

    @Test
    fun `getCasts() should return Success with mapped casts when API call is successful`() =
        runTest {
            // Given
            val movieId = 1
            val castsResponse = MockData.castsResponse
            coEvery { moviesService.getCastList(movieId) } returns castsResponse

            // When
            val subject = moviesRepositoryImpl.getCasts(movieId)

            // Then
            coVerify { moviesService.getCastList(movieId) }
            expectThat(subject)
                .isA<Result.Success<List<Cast>>>()
                .and { get { data }.isEqualTo(MockData.castList) }
        }

    @Test
    fun `getCast() should return 401 Http Error response from API call`() =
        runTest {
            val errorResponse =
                createErrorResponse<CreditsResponse>(401, "Not found")
            coEvery { moviesService.getCastList(any()) } returns errorResponse

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getMovies() }
            expectThat(subject).isA<Result.Error>()
        }

    @Test
    fun `getCasts() should return Error when API call is unsuccessful`() =
        runTest {
            // Given
            val movieId = 1
            val exception = Exception("Something went wrong")
            coEvery { moviesService.getCastList(movieId) } throws exception

            // When
            val subject = moviesRepositoryImpl.getCasts(movieId)

            // Then
            coVerify { moviesService.getCastList(movieId) }
            expectThat(subject)
                .isA<Result.Error>()
                .and { get { message }.isEqualTo("Something went wrong") }
        }

    @Test
    fun `getGenres() should return Success with genres when API call is successful`() =
        runTest {
            // Given
            val genresResponse = MockData.genresResponse
            coEvery { moviesService.getMovies() } returns MockData.movieResponse
            coEvery { moviesService.getGenres() } returns genresResponse

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getGenres() }
            expectThat(subject)
                .isA<Result.Success<List<Movie>>>()
                .and { get { data[0].genreNames }.isEqualTo(listOf("Genre 1", "Genre 2")) }
        }

    @Test
    fun `getGenres() should return Error when API call is unsuccessful`() =
        runTest {
            // Given
            val exception = Exception("Something went wrong")
            coEvery { moviesService.getMovies() } returns MockData.movieResponse
            coEvery { moviesService.getGenres() } throws exception

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getGenres() }
            expectThat(subject)
                .isA<Result.Success<List<Movie>>>()
                .and { get { data[0].genreNames.size }.isEqualTo(0) }
        }

    @Test
    fun `getMovies() should return MalformedDataError when response is null`() =
        runTest {
            // Given
            coEvery { moviesService.getMovies() } returns Response.success(null)
            coEvery { moviesService.getGenres() } returns MockData.genresResponse

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getMovies() }
            expectThat(subject)
                .isA<Result.Error>()
        }

    @Test
    fun `getMovieDetails() should return MalformedDataError when response is null`() =
        runTest {
            // Given
            val movieId = 1
            coEvery { moviesService.getMovieDetails(movieId) } returns Response.success(null)

            // When
            val subject = moviesRepositoryImpl.getMovieDetails(movieId)

            // Then
            coVerify { moviesService.getMovieDetails(movieId) }
            expectThat(subject)
                .isA<Result.Error>()
        }

    @Test
    fun `getCasts() should return MalformedDataError when response is null`() =
        runTest {
            // Given
            val movieId = 1
            coEvery { moviesService.getCastList(movieId) } returns Response.success(null)

            // When
            val subject = moviesRepositoryImpl.getCasts(movieId)

            // Then
            coVerify { moviesService.getCastList(movieId) }
            expectThat(subject)
                .isA<Result.Error>()
        }

    @Test
    fun `getGenres() should return MalformedDataError when response is null`() =
        runTest {
            // Given
            coEvery { moviesService.getMovies() } returns MockData.movieResponse
            coEvery { moviesService.getGenres() } returns Response.success(null)

            // When
            val subject = moviesRepositoryImpl.getMovies()

            // Then
            coVerify { moviesService.getGenres() }
            expectThat(subject)
                .isA<Result.Success<List<Movie>>>()
                .and { get { data[0].genreNames.size }.isEqualTo(0) }
        }
}
