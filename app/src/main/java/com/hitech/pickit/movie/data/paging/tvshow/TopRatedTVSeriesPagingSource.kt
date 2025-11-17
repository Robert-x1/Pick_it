package com.hitech.pickit.movie.data.paging.tvshow

import BasePagingSource
import android.content.Context
import com.hitech.pickit.core.data.networking.safeApiCall
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.movie.data.networking.dto.asTVShowDomainModel
import com.hitech.pickit.movie.data.data_source.remote.TVShowService
import com.hitech.pickit.movie.domain.model.TVShow

class TopRatedTVSeriesPagingSource(context: Context, private val tvShowApi: TVShowService) :
    BasePagingSource<TVShow>(context) {
    override suspend fun fetchItems(page: Int): Result<List<TVShow>, NetworkError > {

        return safeApiCall {
            val response = tvShowApi.topRatedTVSeries(page)
            response.items.asTVShowDomainModel()
        }
    }
}
