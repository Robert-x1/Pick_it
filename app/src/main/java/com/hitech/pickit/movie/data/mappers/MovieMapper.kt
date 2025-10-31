package com.hitech.pickit.movie.data.mappers

import com.hitech.pickit.movie.data.networking.dto.MovieDto
import com.hitech.pickit.movie.domain.Movie


fun MovieDto.toMovie(): Movie{
    return Movie(
        id = id.toString(),
        name = title,
        overview = overview,
        picture =poster_path?.let {posterPath -> "https://image.tmdb.org/t/p/w500$posterPath" },
        rate = vote_average,
        category = genre_ids,
        releaseDate = release_date
    )
}