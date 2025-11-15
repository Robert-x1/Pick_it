package com.hitech.pickit.core.domain.repository.movie.paging

import BasePagingSource
import android.content.Context
import com.hitech.pickit.core.domain.repository.BasePagingRepository
import com.hitech.pickit.movie.data.paging.movie.PopularMoviesPagingSource
import com.hitech.pickit.movie.data.remote.MovieService
import com.hitech.pickit.movie.domain.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieApi: MovieService,
) : BasePagingRepository<Movie>() {
    override fun pagingSource(query: String?, id: Int?): BasePagingSource<Movie> =
        PopularMoviesPagingSource(context, movieApi)
}
