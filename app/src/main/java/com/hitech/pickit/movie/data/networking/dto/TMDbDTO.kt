package com.hitech.pickit.movie.data.networking.dto

import com.hitech.pickit.movie.presentation.models.Movie
import com.hitech.pickit.movie.presentation.models.TVShow
import com.hitech.pickit.movie.utili.Constants.BACKDROP_PATH
import com.hitech.pickit.movie.utili.Constants.BASE_WIDTH_342_PATH
import com.hitech.pickit.movie.utili.Constants.BASE_WIDTH_780_PATH
import com.hitech.pickit.movie.utili.Constants.FIRST_AIR_DATE
import com.hitech.pickit.movie.utili.Constants.GENRE_IDS
import com.hitech.pickit.movie.utili.Constants.ID
import com.hitech.pickit.movie.utili.Constants.NAME
import com.hitech.pickit.movie.utili.Constants.OVERVIEW
import com.hitech.pickit.movie.utili.Constants.POSTER_PATH
import com.hitech.pickit.movie.utili.Constants.RELEASE_DATE
import com.hitech.pickit.movie.utili.Constants.TITLE
import com.hitech.pickit.movie.utili.Constants.VOTE_AVERAGE
import com.hitech.pickit.movie.utili.Constants.VOTE_COUNT
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.String.format


interface TMDbItemResponse {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
    val voteCount: Int

    val genreIds: List<Int>
}

@Serializable
data class MovieResponse(
    @SerialName( ID)
    override val id: Int,
    @SerialName( OVERVIEW)
    override val overview: String,
    @SerialName( RELEASE_DATE)
    override val releaseDate: String?,
    @SerialName( POSTER_PATH)
    override val posterPath: String?,
    @SerialName( BACKDROP_PATH)
    override val backdropPath: String?,
    @SerialName( TITLE)
    override val name: String,
    @SerialName( VOTE_AVERAGE)
    override val voteAverage: Double,
    @SerialName( VOTE_COUNT)
    override val voteCount: Int,
    @SerialName( GENRE_IDS)
    override val genreIds: List<Int>,
) : TMDbItemResponse

@Serializable
data class TVShowResponse(
    @SerialName( ID)
    override val id: Int,
    @SerialName( OVERVIEW)
    override val overview: String,
    @SerialName( FIRST_AIR_DATE)
    override val releaseDate: String?,
    @SerialName( POSTER_PATH)
    override val posterPath: String?,
    @SerialName( BACKDROP_PATH)
    override val backdropPath: String?,
    @SerialName( NAME)
    override val name: String,
    @SerialName( VOTE_AVERAGE)
    override val voteAverage: Double,
    @SerialName( VOTE_COUNT)
    override val voteCount: Int,
    @SerialName( GENRE_IDS)
    override val genreIds: List<Int>
) : TMDbItemResponse

fun List<MovieResponse>.asMovieDomainModel() = map(MovieResponse::asMovieDomainModel)

fun List<TVShowResponse>.asTVShowDomainModel() = map(TVShowResponse::asTVShowDomainModel)

private fun MovieResponse.asMovieDomainModel(): Movie = Movie(
    id,
    overview,
    releaseDate,
    posterPath?.let { posterPath ->
        format(
            BASE_WIDTH_342_PATH,
            posterPath,
        )
    },
    backdropPath?.let { backdropPath ->
        format(
            BASE_WIDTH_780_PATH,
            backdropPath,
        )
    },
    name,
    voteAverage,
    voteCount,
    genreIds
)

private fun TVShowResponse.asTVShowDomainModel(): TVShow = TVShow(
    id,
    overview,
    releaseDate,
    posterPath?.let { posterPath ->
        format(
            BASE_WIDTH_342_PATH,
            posterPath,
        )
    },
    backdropPath?.let { backdropPath ->
        format(
            BASE_WIDTH_780_PATH,
            backdropPath,
        )
    },
    name,
    voteAverage,
    voteCount,
    genreIds
)
