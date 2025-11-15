package com.hitech.pickit.core.domain.repository.movie.detail

import com.hitech.pickit.core.data.source.entity.asDomainModel
import com.hitech.pickit.core.data.source.local.MovieDao
import com.hitech.pickit.di.IoDispatcher
import com.hitech.pickit.movie.domain.model.Movie
import com.hitech.pickit.movie.presentation.models.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class BookmarkMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    //@ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<List<@JvmSuppressWildcards Movie>>(ioDispatcher) {
    override suspend fun getSuccessResult(id: Any?): List<Movie> = movieDao.getBookmarks().asDomainModel()
}
