package com.hitech.pickit.core.domain.repository.tvshow.detail

import com.hitech.pickit.core.data.source.entity.asDomainModel
import com.hitech.pickit.core.data.source.local.TVShowDao
import com.hitech.pickit.di.IoDispatcher
import com.hitech.pickit.movie.domain.model.TVShow
import com.hitech.pickit.movie.presentation.models.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class BookmarkTVShowRepository @Inject constructor(
    private val tvShowDao: TVShowDao,
    //@ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<List<@JvmSuppressWildcards TVShow>>(ioDispatcher) {
    override suspend fun getSuccessResult(id: Any?): List<TVShow> = tvShowDao.getBookmarks().asDomainModel()
}
