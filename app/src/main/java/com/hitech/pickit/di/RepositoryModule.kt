package com.hitech.pickit.di

import com.hitech.pickit.core.domain.repository.MovieRepositoryImpl
import com.hitech.pickit.movie.data.data_source.local.LocalDataSource
import com.hitech.pickit.movie.data.data_source.remote.MovieService
import com.hitech.pickit.movie.data.repository.ProfileRepositoryImpl
import com.hitech.pickit.movie.domain.repository.MovieRepository
import com.hitech.pickit.movie.domain.repository.ProfileRepository
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
    fun provideMovieRepository(api: MovieService): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(localDataSource: LocalDataSource): ProfileRepository {
        return ProfileRepositoryImpl(localDataSource)
    }
}
