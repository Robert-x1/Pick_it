package com.hitech.pickit.movie.domain.use_case

import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.movie.domain.Movie
import com.hitech.pickit.movie.domain.repository.MovieRepository

class DiscoverMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int): Result<List<Movie>, NetworkError> {
        return repository.discoverMovie(page)
    }
}