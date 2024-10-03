package com.vishesh.data.di

import com.vishesh.common.CoroutineDispatcherProvider
import com.vishesh.data.repositories.MoviesRepositoryImpl
import com.vishesh.data.services.MoviesService
import com.vishesh.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {
    @Provides
    @ViewModelScoped
    fun provideMoviesRepository(
        movieService: MoviesService,
        dispatcherProvider: CoroutineDispatcherProvider,
    ): MoviesRepository =
        MoviesRepositoryImpl(
            movieService,
            dispatcherProvider,
        )
}
