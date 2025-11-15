package com.hitech.pickit.movie.utili

import androidx.compose.runtime.Immutable
import com.hitech.pickit.movie.domain.model.Movie
import com.hitech.pickit.movie.presentation.models.MovieUi

@Immutable
data class MovieState(

    val isLoading: Boolean = false,
    val movies: List<MovieUi> = emptyList(),
    val selectedMovie: Movie? = null,
    val error: String? = null
)