package com.hitech.pickit.movie.domain.repository

import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.movie.domain.Movie

interface MovieRepository {
    suspend fun discoverMovie(page: Int): Result<List<Movie>, NetworkError>
}