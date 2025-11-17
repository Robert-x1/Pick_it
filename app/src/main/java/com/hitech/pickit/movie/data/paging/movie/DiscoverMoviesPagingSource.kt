package com.hitech.pickit.movie.data.paging.movie

import BasePagingSource
import android.content.Context
import com.hitech.pickit.core.data.networking.safeApiCall
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.movie.data.networking.dto.asMovieDomainModel
import com.hitech.pickit.movie.data.data_source.remote.MovieService
import com.hitech.pickit.movie.domain.model.Movie

class DiscoverMoviesPagingSource(context: Context, private val movieApi: MovieService) :
    BasePagingSource<Movie>(context) {
    override suspend fun fetchItems(page: Int): Result<List<Movie>, NetworkError > {

            return safeApiCall {
                val response = movieApi.discoverMovies(page)
                response.items.asMovieDomainModel()
            }

    }
}
