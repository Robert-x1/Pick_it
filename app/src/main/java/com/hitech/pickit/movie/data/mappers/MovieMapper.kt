package com.hitech.pickit.movie.data.mappers

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.hitech.pickit.R
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.movie.data.networking.dto.MovieResponse
import com.hitech.pickit.movie.domain.model.Movie
import com.hitech.pickit.movie.utili.Constants.BASE_WIDTH_500_PATH


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
@Composable
fun NetworkError.toUserMessage(): String {
    val context = LocalContext.current

    return when (this) {
        NetworkError.REQUEST_TIMEOUT -> context.getString(R.string.error_request_timeout)
        NetworkError.TOO_MANY_REQUESTS -> context.getString(R.string.too_many_requests)
        NetworkError.NO_INTERNET -> context.getString(R.string.no_internet)
        NetworkError.SERVER_ERROR -> context.getString(R.string.server_error)
        NetworkError.SERIALIZATION -> context.getString(R.string.serialization_error)
        NetworkError.UNKNOW -> context.getString(R.string.unknow_error)
    }
}