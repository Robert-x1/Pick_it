package com.hitech.pickit.movie.presentation.models

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.hitech.pickit.movie.domain.Movie
import java.util.Locale

data class MovieUi(
    val id: String,
    val name: String,
    @DrawableRes
    val picture: Int,
    val rank: Int,
    val rate: DisplayableNumber,
    val ticketPrice: DisplayableNumber,
    val category: List<String>

)

data class DisplayableNumber(
    val value: Double,
    val formatted: String

)
fun Movie.toMovieUi() : MovieUi{
    return MovieUi(
        id = id,
        name = name,
        picture = picture,
        rank = rank,
        rate = rate.toDisplayableNumber(),
        ticketPrice = ticketPrice.toDisplayableNumber(),
        category = category
    )


}

fun Double.toDisplayableNumber(): DisplayableNumber{
    val formatted = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 1
        minimumFractionDigits = 1
    }
    return DisplayableNumber(
        value = this,
        formatted = formatted.format(this)
    )

}
