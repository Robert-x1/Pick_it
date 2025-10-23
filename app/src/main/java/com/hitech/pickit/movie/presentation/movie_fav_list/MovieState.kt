package com.robert.pickit.movie.presentation.movie_list

import androidx.compose.runtime.Immutable
import com.hitech.pickit.movie.domain.Movie
import com.hitech.pickit.movie.presentation.models.MovieUi


@Immutable
data class MovieState(

    val isLoading: Boolean = false,
    val movies: List<MovieUi> = emptyList(),
    val selectedCoin: Movie? = null,
    val error: String? = null
)