package com.hitech.pickit.core.domain.repository

import com.hitech.pickit.core.data.networking.safeApiCall
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.movie.data.mappers.toMovie
import com.hitech.pickit.movie.data.remote.tmdpApi
import com.hitech.pickit.movie.domain.Movie
import com.hitech.pickit.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    val tmdpApi: tmdpApi
) : MovieRepository {
    override suspend fun discoverMovie(page: Int): Result<List<Movie>, NetworkError> {
        return safeApiCall {
            tmdpApi.discoverMovie(page).results.map { movieDto -> movieDto.toMovie() }
        }
    }
}