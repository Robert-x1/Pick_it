package com.hitech.pickit.di

import com.hitech.pickit.movie.domain.repository.MovieRepository
import com.hitech.pickit.movie.domain.use_case.DiscoverMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideDiscoverMoviesUseCase(repository: MovieRepository): DiscoverMoviesUseCase {
        return DiscoverMoviesUseCase(repository)
    }
}