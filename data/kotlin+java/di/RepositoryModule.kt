package com.nazaroi.data.di

import com.nazaroi.data.repository.MoviesRepositoryImpl
import com.nazaroi.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository
}