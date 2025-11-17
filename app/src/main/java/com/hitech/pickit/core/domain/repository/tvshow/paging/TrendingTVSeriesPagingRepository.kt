package com.hitech.pickit.core.domain.repository.tvshow.paging

import BasePagingSource
import android.content.Context
import com.hitech.pickit.core.domain.repository.BasePagingRepository
import com.hitech.pickit.movie.data.paging.tvshow.TrendingTVSeriesPagingSource
import com.hitech.pickit.movie.data.data_source.remote.TVShowService
import com.hitech.pickit.movie.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingTVSeriesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tvShowApi: TVShowService,
) : BasePagingRepository<TVShow>() {
    override fun pagingSource(query: String?, id: Int?): BasePagingSource<TVShow> =
        TrendingTVSeriesPagingSource(context, tvShowApi)
}
