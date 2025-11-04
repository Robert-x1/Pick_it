package com.hitech.pickit.movie.data.networking.dto

data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double,
    val genre_ids: List<Int>

)
