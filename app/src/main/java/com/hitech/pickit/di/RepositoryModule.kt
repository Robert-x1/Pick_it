package com.hitech.pickit.di

import com.hitech.pickit.core.domain.repository.BaseDetailRepository
import com.hitech.pickit.core.domain.repository.BaseFeedRepository
import com.hitech.pickit.core.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.core.domain.repository.PersonRepository
import com.hitech.pickit.core.domain.repository.movie.MovieFeedRepository
import com.hitech.pickit.core.domain.repository.movie.detail.BookmarkMovieDetailsRepositoryImpl
import com.hitech.pickit.core.domain.repository.movie.detail.MovieDetailRepository
import com.hitech.pickit.core.domain.repository.profile.ProfileRepository
import com.hitech.pickit.core.domain.repository.profile.ProfileRepositoryImpl
import com.hitech.pickit.core.domain.repository.tvshow.TVShowFeedRepository
import com.hitech.pickit.core.domain.repository.tvshow.detail.BookmarkTVShowDetailsRepositoryImpl
import com.hitech.pickit.core.domain.repository.tvshow.detail.TVShowDetailRepository
import com.hitech.pickit.movie.domain.model.Movie
import com.hitech.pickit.movie.domain.model.MovieDetails
import com.hitech.pickit.movie.domain.model.Person
import com.hitech.pickit.movie.domain.model.TVShow
import com.hitech.pickit.movie.domain.model.TVShowDetails
import com.hitech.pickit.movie.presentation.models.base.BaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindsModule {

    @Binds
    @Singleton
    abstract fun bindMovieBookmarkRepository(
        impl: BookmarkMovieDetailsRepositoryImpl
    ): BookmarkDetailsRepository<Movie>

    @Binds
    @Singleton
    abstract fun bindTVShowBookmarkRepository(
        impl: BookmarkTVShowDetailsRepositoryImpl
    ): BookmarkDetailsRepository<TVShow>

    @Binds
    @Singleton
    abstract fun bindPersonRepository(
        impl: PersonRepository
    ): BaseRepository<Person>


    @Binds
    @Singleton
    abstract fun bindMovieDetailRepository(
        impl: MovieDetailRepository
    ): BaseDetailRepository<MovieDetails>

    @Binds
    @Singleton
    abstract fun bindTVShowDetailRepository(
        impl: TVShowDetailRepository
    ): BaseDetailRepository<TVShowDetails>

    @Binds
    @Singleton
    abstract fun bindMovieFeedRepository(
        impl: MovieFeedRepository
    ): BaseFeedRepository<Movie>

    @Binds
    @Singleton
    abstract fun bindTVShowFeedRepository(
        impl: TVShowFeedRepository
    ): BaseFeedRepository<TVShow>

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        impl: ProfileRepositoryImpl
    ): ProfileRepository

}
