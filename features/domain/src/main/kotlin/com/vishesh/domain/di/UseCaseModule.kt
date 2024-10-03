package com.vishesh.domain.di

import com.vishesh.domain.repositories.MoviesRepository
import com.vishesh.domain.usecases.GetCastsUseCase
import com.vishesh.domain.usecases.GetMovieDetailsUseCase
import com.vishesh.domain.usecases.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetCastsUseCase(repository: MoviesRepository) = GetCastsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetMovieDetailsUseCase(repository: MoviesRepository) = GetMovieDetailsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetMoviesUseCase(repository: MoviesRepository) = GetMoviesUseCase(repository)
}
