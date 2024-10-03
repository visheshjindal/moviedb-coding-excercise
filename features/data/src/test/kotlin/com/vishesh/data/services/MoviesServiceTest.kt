package com.vishesh.data.services

import com.vishesh.data.models.CreditsResponse
import com.vishesh.data.models.GenresResponse
import com.vishesh.data.models.MovieDetailsResponse
import com.vishesh.data.models.MoviesResponse
import com.vishesh.data.testdata.MockData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesServiceTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var moviesService: MoviesService
    private val json = Json { ignoreUnknownKeys = true }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        mockWebServer = MockWebServer()
        val retrofit =
            Retrofit
                .Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        moviesService = retrofit.create(MoviesService::class.java)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()

        mockWebServer.shutdown()
    }

    @Test
    fun `getMovies returns successful response`() =
        runTest {
            // Given
            val mockResponse = MockData.movieResponse.body()
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(json.encodeToString(mockResponse)),
            )

            // When
            val subject = moviesService.getMovies()

            // Then
            expectThat(subject) {
                get { code() }.isEqualTo(200)
                get { body() }
                    .isA<MoviesResponse>()
                    .isEqualTo(mockResponse)
            }
        }

    @Test
    fun `getGenres returns successful response`() =
        runTest {
            // Given
            val mockResponse = MockData.genresResponse.body()
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(Json.encodeToString(mockResponse)),
            )

            // When
            val response = moviesService.getGenres()

            // Then
            expectThat(response) {
                get { code() }.isEqualTo(200)
                get { body() }
                    .isA<GenresResponse>()
                    .isEqualTo(mockResponse)
            }
        }

    @Test
    fun `getMovies returns error response`() =
        runTest {
            // Given
            val errorMessage = "Internal server error"
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(500)
                    .setBody(errorMessage),
            )

            // When
            val subject = moviesService.getMovies()

            // Then
            expectThat(subject) {
                get { code() }.isEqualTo(500)
                get { errorBody()?.string() }.isEqualTo(errorMessage)
            }
        }

    @Test
    fun `getGenres returns error response`() =
        runTest {
            // Given
            val errorMessage = "Internal server error"
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(500)
                    .setBody(errorMessage),
            )

            // When
            val response = moviesService.getGenres()

            // Then
            expectThat(response) {
                get { code() }.isEqualTo(500)
                get { errorBody()?.string() }.isEqualTo(errorMessage)
            }
        }

    @Test
    fun `getCastList returns successful response`() =
        runTest {
            // Given
            val mockResponse = MockData.castsResponse.body()
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(Json.encodeToString(mockResponse)),
            )

            // When
            val response = moviesService.getCastList(1)

            // Then
            expectThat(response) {
                get { code() }.isEqualTo(200)
                get { body() }
                    .isA<CreditsResponse>()
                    .isEqualTo(mockResponse)
            }
        }

    @Test
    fun `getMovieDetails returns successful response`() =
        runTest {
            // Given
            val mockResponse = MockData.movieDetailsResponse.body()
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(Json.encodeToString(mockResponse)),
            )

            // When
            val response = moviesService.getMovieDetails(1)

            // Then
            expectThat(response) {
                get { code() }.isEqualTo(200)
                get { body() }
                    .isA<MovieDetailsResponse>()
                    .isEqualTo(mockResponse)
            }
        }
}
