package com.hitech.pickit.movie.domain

import androidx.annotation.DrawableRes

data class Movie(
    val id: String,
    val name: String,
    @DrawableRes
    val picture: Int,
    val rank: Int,
    val rate: Double,
    val ticketPrice: Double,
    val category: List<String>,

)