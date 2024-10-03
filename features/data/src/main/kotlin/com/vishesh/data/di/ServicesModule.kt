package com.vishesh.data.di

import com.vishesh.data.services.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal class ServicesModule {
    @Provides
    @ViewModelScoped
    fun provideMovieService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)
}
