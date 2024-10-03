package com.vishesh.network.di

import com.vishesh.common.CoroutineDispatcherProvider
import com.vishesh.network.utils.DefaultDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineDispatcherModule {
    @Binds
    @Singleton
    fun provideDispatchers(dispatchers: DefaultDispatcherProvider): CoroutineDispatcherProvider
}
