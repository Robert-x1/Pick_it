package com.hitech.pickit.core.domain.repository.movie

import com.hitech.pickit.R
import com.hitech.pickit.core.domain.repository.BaseFeedRepository
import com.hitech.pickit.di.IoDispatcher
import com.hitech.pickit.movie.data.networking.dto.asMovieDomainModel
import com.hitech.pickit.movie.data.remote.MovieService
import com.hitech.pickit.movie.domain.model.Movie
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class MovieFeedRepository @Inject constructor(
    //@ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val movieApi: MovieService,
) : BaseFeedRepository<Movie>(ioDispatcher) {
    override suspend fun popularItems(): List<Movie> = movieApi.popularMovies().items.asMovieDomainModel()

    override suspend fun latestItems(): List<Movie> = movieApi.upcomingMovies().items.asMovieDomainModel()

    override suspend fun topRatedItems(): List<Movie> = movieApi.topRatedMovies().items.asMovieDomainModel()

    override suspend fun trendingItems(): List<Movie> = movieApi.trendingMovies().items.asMovieDomainModel()

    override suspend fun nowPlayingItems(): List<Movie> = movieApi.nowPlayingMovies().items.asMovieDomainModel()

    override suspend fun discoverItems(): List<Movie> = movieApi.discoverMovies().items.asMovieDomainModel()

    override fun getNowPlayingResId(): Int = R.string.text_now_playing

    override fun getLatestResId(): Int = R.string.text_upcoming
}
