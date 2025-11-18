package com.hitech.pickit.di

import android.content.Context
import com.hitech.pickit.core.domain.repository.BasePagingRepository
import com.hitech.pickit.core.domain.repository.movie.paging.DiscoverMoviesPagingRepository
import com.hitech.pickit.core.domain.repository.movie.paging.NowPlayingMoviesPagingRepository
import com.hitech.pickit.core.domain.repository.movie.paging.PopularMoviesPagingRepository
import com.hitech.pickit.core.domain.repository.movie.paging.SearchMoviesPagingRepository
import com.hitech.pickit.core.domain.repository.movie.paging.SimilarMoviesPagingRepository
import com.hitech.pickit.core.domain.repository.movie.paging.TopRatedMoviesPagingRepository
import com.hitech.pickit.core.domain.repository.movie.paging.TrendingMoviesPagingRepository
import com.hitech.pickit.core.domain.repository.movie.paging.UpcomingMoviesPagingRepository
import com.hitech.pickit.core.domain.repository.tvshow.paging.AiringTodayTVSeriesPagingRepository
import com.hitech.pickit.core.domain.repository.tvshow.paging.DiscoverTVSeriesPagingRepository
import com.hitech.pickit.core.domain.repository.tvshow.paging.OnTheAirTVSeriesPagingRepository
import com.hitech.pickit.core.domain.repository.tvshow.paging.PopularTVSeriesPagingRepository
import com.hitech.pickit.core.domain.repository.tvshow.paging.SearchTVSeriesPagingRepository
import com.hitech.pickit.core.domain.repository.tvshow.paging.SimilarTVSeriesPagingRepository
import com.hitech.pickit.core.domain.repository.tvshow.paging.TopRatedTVSeriesPagingRepository
import com.hitech.pickit.core.domain.repository.tvshow.paging.TrendingTVSeriesPagingRepository
import com.hitech.pickit.movie.data.remote.MovieService
import com.hitech.pickit.movie.data.remote.TVShowService
import com.hitech.pickit.movie.domain.model.Movie
import com.hitech.pickit.movie.domain.model.TVShow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingRepositoryModule {

    @Provides
    @Singleton
    @NowPlaying
    fun provideNowPlayingMoviesRepository(
        @ApplicationContext context: Context,
        movieApi: MovieService
    ): BasePagingRepository<Movie> = NowPlayingMoviesPagingRepository(context, movieApi)

    @Provides
    @Singleton
    @Discover
    fun provideDiscoverMoviesRepository(
        @ApplicationContext context: Context,
        api: MovieService
    ): BasePagingRepository<Movie> {
        return DiscoverMoviesPagingRepository(context, api)
    }

    @Provides
    @Singleton
    @Discover
    fun provideDiscoverTVShowRepository(
        @ApplicationContext context: Context,
        api: TVShowService
    ): BasePagingRepository<TVShow> {
        return DiscoverTVSeriesPagingRepository(context, api)
    }

    @Provides
    @Singleton
    @NowPlaying
    fun provideNowPlayingTVShowRepository(
        @ApplicationContext context: Context,
        api: TVShowService
    ): BasePagingRepository<TVShow> {
        return OnTheAirTVSeriesPagingRepository(context, api)
    }

    @Provides
    @Singleton
    @AiringToday
    fun provideAiringTodayTvSeriesRepository(
        @ApplicationContext context: Context,
        tvShowApi: TVShowService
    ): BasePagingRepository<TVShow> = AiringTodayTVSeriesPagingRepository(context, tvShowApi)

    @Provides
    @Singleton
    @Popular
    fun providePopularTvSeriesRepository(
        @ApplicationContext context: Context,
        tvShowApi: TVShowService
    ): BasePagingRepository<TVShow> = PopularTVSeriesPagingRepository(context, tvShowApi)

    @Provides
    @Singleton
    @Popular
    fun providePopularMoviesRepository(
        @ApplicationContext context: Context,
        movieApi: MovieService
    ): BasePagingRepository<Movie> = PopularMoviesPagingRepository(context, movieApi)

    @Provides
    @Singleton
    @Trending
    fun provideTrendingTvSeriesRepository(
        @ApplicationContext context: Context,
        tvShowApi: TVShowService
    ): BasePagingRepository<TVShow> = TrendingTVSeriesPagingRepository(context, tvShowApi)

    @Provides
    @Singleton
    @Trending
    fun provideTrendingMoviesRepository(
        @ApplicationContext context: Context,
        movieApi: MovieService
    ): BasePagingRepository<Movie> = TrendingMoviesPagingRepository(context, movieApi)

    @Provides
    @Singleton
    @TopRated
    fun provideTopRatedTvSeriesRepository(
        @ApplicationContext context: Context,
        tvShowApi: TVShowService
    ): BasePagingRepository<TVShow> = TopRatedTVSeriesPagingRepository(context, tvShowApi)

    @Provides
    @Singleton
    @TopRated
    fun provideTopRatedMoviesRepository(
        @ApplicationContext context: Context,
        movieApi: MovieService
    ): BasePagingRepository<Movie> = TopRatedMoviesPagingRepository(context, movieApi)

    @Provides
    @Singleton
    @Similar
    fun provideSimilarTvSeriesRepository(
        @ApplicationContext context: Context,
        tvShowApi: TVShowService
    ): BasePagingRepository<TVShow> = SimilarTVSeriesPagingRepository(context, tvShowApi)

    @Provides
    @Singleton
    @Similar
    fun provideSimilarMoviesRepository(
        @ApplicationContext context: Context,
        movieApi: MovieService
    ): BasePagingRepository<Movie> = SimilarMoviesPagingRepository(context, movieApi)

    @Provides
    @Singleton
    @Search
    fun provideSearchTvSeriesRepository(
        @ApplicationContext context: Context,
        tvShowApi: TVShowService
    ): BasePagingRepository<TVShow> = SearchTVSeriesPagingRepository(context, tvShowApi)

    @Provides
    @Singleton
    @Search
    fun provideSearchMoviesRepository(
        @ApplicationContext context: Context,
        movieApi: MovieService
    ): BasePagingRepository<Movie> = SearchMoviesPagingRepository(context, movieApi)

    @Provides
    @Singleton
    @Latest
    fun provideLatestMoviesRepository(
        @ApplicationContext context: Context,
        movieApi: MovieService
    ): BasePagingRepository<Movie> = UpcomingMoviesPagingRepository(context, movieApi)

    @Provides
    @Singleton
    @Latest
    fun provideLatestTVShowRepository(
        @ApplicationContext context: Context,
        api: TVShowService
    ): BasePagingRepository<TVShow> {
        return OnTheAirTVSeriesPagingRepository(context, api)
    }

    @Provides
    @Singleton
    @Upcoming
    fun provideUpcomingMoviesRepository(
        @ApplicationContext context: Context,
        movieApi: MovieService
    ): BasePagingRepository<Movie> {
        return UpcomingMoviesPagingRepository(context, movieApi)
    }

    @Provides
    @Singleton
    @OnTheAir
    fun provideOnTheAirTvSeriesRepository(
        @ApplicationContext context: Context,
        api: TVShowService
    ): BasePagingRepository<TVShow> {
        return OnTheAirTVSeriesPagingRepository(context, api)
    }

}

