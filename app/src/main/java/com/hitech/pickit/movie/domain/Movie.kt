package com.hitech.pickit.movie.domain


data class Movie(
    val id: String,
    val name: String,
    val picture: String?,
    val rate: Double,
    val overview: String,
    val releaseDate: String?,
    val category: List<Int>
    )