package com.hitech.pickit.core.domain.repository

import com.hitech.pickit.core.data.networking.safeApiCall
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.movie.data.mappers.toMovie
import com.hitech.pickit.movie.data.data_source.remote.MovieService
import com.hitech.pickit.movie.domain.repository.MovieRepository
import com.hitech.pickit.movie.domain.model.Movie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    val movieService: MovieService
) : MovieRepository {

    override suspend fun discoverMovie(page: Int): Result<List<Movie>, NetworkError> {
        return safeApiCall {
            movieService.discoverMovies(page).items.map { movieDto -> movieDto.toMovie() }
        }
    }
    suspend fun popularMovies(page: Int): Result<List<Movie>, NetworkError> {
        return safeApiCall {
            movieService.popularMovies(page).items.map { movieDto -> movieDto.toMovie() }
        }
    }
}