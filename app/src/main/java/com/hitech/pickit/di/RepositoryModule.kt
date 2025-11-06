package com.hitech.pickit.di

import com.hitech.pickit.core.domain.repository.MovieRepositoryImpl
import com.hitech.pickit.movie.data.remote.tmdpApi
import com.hitech.pickit.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: tmdpApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}
