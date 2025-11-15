package com.hitech.pickit.movie.data.networking.dto

import com.hitech.pickit.movie.presentation.models.Genre
import com.hitech.pickit.movie.presentation.models.MovieDetails
import com.hitech.pickit.movie.presentation.models.SpokenLanguage
import com.hitech.pickit.movie.presentation.models.TVShowDetails
import com.hitech.pickit.movie.utili.Constants.BACKDROP_PATH
import com.hitech.pickit.movie.utili.Constants.BASE_WIDTH_342_PATH
import com.hitech.pickit.movie.utili.Constants.BASE_WIDTH_780_PATH
import com.hitech.pickit.movie.utili.Constants.FIRST_AIR_DATE
import com.hitech.pickit.movie.utili.Constants.ID
import com.hitech.pickit.movie.utili.Constants.NAME
import com.hitech.pickit.movie.utili.Constants.OVERVIEW
import com.hitech.pickit.movie.utili.Constants.POSTER_PATH
import com.hitech.pickit.movie.utili.Constants.RELEASE_DATE
import com.hitech.pickit.movie.utili.Constants.VOTE_AVERAGE
import com.hitech.pickit.movie.utili.Constants.VOTE_COUNT
import com.hitech.pickit.movie.utili.Constants.TITLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


interface TMDbItemDetailsResponse {
    val backdropPath: String?
    val genres: List<GenreResponse>
    val homepage: String?
    val id: Int
    val originalLanguage: String
    val originalTitle: String
    val overview: String
    val popularity: Double
    val posterPath: String?
    val releaseDate: String?
    val spokenLanguages: List<SpokenLanguageResponse>
    val status: String
    val tagline: String
    val title: String
    val voteAverage: Double
    val voteCount: Int
}

@Serializable
data class MovieDetailResponse(
    @SerialName( BACKDROP_PATH) override val backdropPath: String?,
    @SerialName( GENRES) override val genres: List<GenreResponse>,
    @SerialName( HOMEPAGE) override val homepage: String?,
    @SerialName( ID) override val id: Int,
    @SerialName( ORIGINAL_LANGUAGE) override val originalLanguage: String,
    @SerialName( ORIGINAL_TITLE) override val originalTitle: String,
    @SerialName( OVERVIEW) override val overview: String,
    @SerialName( POPULARITY) override val popularity: Double,
    @SerialName( POSTER_PATH) override val posterPath: String?,
    @SerialName( RELEASE_DATE) override val releaseDate: String?,
    @SerialName( SPOKEN_LANGUAGE) override val spokenLanguages: List<SpokenLanguageResponse>,
    @SerialName( STATUS) override val status: String,
    @SerialName( TAGLINE) override val tagline: String,
    @SerialName( TITLE) override val title: String,
    @SerialName( VOTE_AVERAGE) override val voteAverage: Double,
    @SerialName( VOTE_COUNT) override val voteCount: Int,
) : TMDbItemDetailsResponse

@Serializable
data class TvDetailResponse(
    @SerialName( BACKDROP_PATH) override val backdropPath: String?,
    @SerialName( GENRES) override val genres: List<GenreResponse>,
    @SerialName( HOMEPAGE) override val homepage: String?,
    @SerialName( ID) override val id: Int,
    @SerialName( ORIGINAL_LANGUAGE) override val originalLanguage: String,
    @SerialName( ORIGINAL_NAME) override val originalTitle: String,
    @SerialName( OVERVIEW) override val overview: String,
    @SerialName( POPULARITY) override val popularity: Double,
    @SerialName( POSTER_PATH) override val posterPath: String?,
    @SerialName( FIRST_AIR_DATE) override val releaseDate: String?,
    @SerialName( SPOKEN_LANGUAGE) override val spokenLanguages: List<SpokenLanguageResponse>,
    @SerialName( STATUS) override val status: String,
    @SerialName( TAGLINE) override val tagline: String,
    @SerialName( NAME) override val title: String,
    @SerialName( VOTE_AVERAGE) override val voteAverage: Double,
    @SerialName( VOTE_COUNT) override val voteCount: Int,
) : TMDbItemDetailsResponse

@Serializable
data class GenreResponse(@SerialName( ID) val id: Int, @SerialName( NAME) val name: String?)

@Serializable
data class SpokenLanguageResponse(@SerialName( "iso_639_1") val iso6391: String, @SerialName( NAME) val name: String)

fun MovieDetailResponse.asDomainModel(): MovieDetails = MovieDetails(
    backdropPath?.let {
        String.format(
            BASE_WIDTH_780_PATH,
            it,
        )
    },
    genres.asGenreDomainModel(),
    homepage,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath?.let {
        String.format(
            BASE_WIDTH_342_PATH,
            it,
        )
    },
    releaseDate,
    spokenLanguages.asLanguageDomainModel(),
    status,
    tagline,
    title,
    voteAverage,
    voteCount,
)

fun TvDetailResponse.asDomainModel(): TVShowDetails = TVShowDetails(
    backdropPath?.let {
        String.format(
            BASE_WIDTH_780_PATH,
            it,
        )
    },
    genres.asGenreDomainModel(),
    homepage,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath?.let {
        String.format(
            BASE_WIDTH_342_PATH,
            it,
        )
    },
    releaseDate,
    spokenLanguages.asLanguageDomainModel(),
    status,
    tagline,
    title,
    voteAverage,
    voteCount,
)

private fun List<GenreResponse>.asGenreDomainModel(): List<Genre> = map {
    Genre(it.id, it.name)
}

private fun List<SpokenLanguageResponse>.asLanguageDomainModel(): List<SpokenLanguage> = map {
    SpokenLanguage(it.iso6391, it.name)
}

private const val GENRES = "genres"
private const val HOMEPAGE = "homepage"
private const val ORIGINAL_LANGUAGE = "original_language"
private const val ORIGINAL_TITLE = "original_title"
private const val ORIGINAL_NAME = "original_name"
private const val POPULARITY = "popularity"
private const val SPOKEN_LANGUAGE = "spoken_languages"
private const val STATUS = "status"
private const val TAGLINE = "tagline"
