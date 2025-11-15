package com.hitech.pickit.movie.data.mappers

import com.hitech.pickit.movie.data.networking.dto.MovieResponse
import com.hitech.pickit.movie.presentation.models.Movie
import com.hitech.pickit.movie.utili.Constants.BASE_WIDTH_500_PATH
import com.hitech.pickit.movie.utili.Constants.BASE_WIDTH_780_PATH


fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = id,
        name = name,
        overview = overview,
        posterUrl = posterPath?.let { posterPath -> BASE_WIDTH_500_PATH+posterPath },
        voteAverage = voteAverage,
        genreIds = genreIds,
        releaseDate = releaseDate,
        voteCount = voteCount,
        backdropUrl = backdropPath?.let { backdropPath -> BASE_WIDTH_500_PATH+backdropPath },

        )
}