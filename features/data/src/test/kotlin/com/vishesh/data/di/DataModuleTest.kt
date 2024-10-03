package com.vishesh.data.di

import com.vishesh.common.CoroutineDispatcherProvider
import com.vishesh.data.services.MoviesService
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isNotNull

class DataModuleTest {
    @Test
    fun `verify provideMoviesRepository returns a non-null MoviesRepository instance`() {
        // Arrange
        val dataModule = mockk<DataModule>(relaxed = true)
        val mockRepository = mockk<MoviesService>()
        val dispatcherProvider = mockk<CoroutineDispatcherProvider>()
        // Act
        val subject = dataModule.provideMoviesRepository(mockRepository, dispatcherProvider)
        // Assert
        expectThat(subject).isNotNull()
    }
}
