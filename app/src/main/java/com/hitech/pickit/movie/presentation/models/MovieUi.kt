package com.hitech.pickit.movie.presentation.models

import android.icu.text.NumberFormat
import com.hitech.pickit.movie.domain.Movie
import java.util.Locale

data class MovieUi(
    val id: String,
    val name: String,
    val picture: String?,
    val rate: DisplayableNumber,
    val category: List<Int>,
    val overview: String,
    val releaseDate: String?,

)

data class DisplayableNumber(
    val value: Double,
    val formatted: String

)

fun Movie.toMovieUi(): MovieUi {
    return MovieUi(
        id = id,
        name = name,
        picture = picture,
        rate = rate.toDisplayableNumber(),
        category = category,
        overview = overview,
        releaseDate = releaseDate,
    )


}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatted = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 1
        minimumFractionDigits = 1
    }
    return DisplayableNumber(
        value = this,
        formatted = formatted.format(this)
    )

}
