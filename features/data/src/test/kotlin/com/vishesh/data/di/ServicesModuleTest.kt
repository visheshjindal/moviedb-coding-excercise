package com.vishesh.data.di

import com.vishesh.data.services.MoviesService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import strikt.api.expectThat
import strikt.assertions.isNotNull

class ServicesModuleTest {
    @Test
    fun `verify provideMovieService returns a non-null MoviesService instance`() {
        // Arrange
        val mockRetrofit: Retrofit = mockk(relaxed = true)
        val servicesModule = ServicesModule()
        every { mockRetrofit.create(MoviesService::class.java) } returns mockk()

        // Act
        val movieService = servicesModule.provideMovieService(mockRetrofit)

        // Assert
        expectThat(movieService).isNotNull()
        verify { mockRetrofit.create(MoviesService::class.java) }
    }
}
