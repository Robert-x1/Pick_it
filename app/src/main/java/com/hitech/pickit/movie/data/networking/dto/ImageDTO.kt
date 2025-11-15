package com.hitech.pickit.movie.data.networking.dto

import com.hitech.pickit.movie.presentation.models.TMDbImage
import com.hitech.pickit.movie.utili.Constants.BASE_IMAGE_PATH
import com.hitech.pickit.movie.utili.Constants.ID
import com.hitech.pickit.movie.utili.Constants.VOTE_AVERAGE
import com.hitech.pickit.movie.utili.Constants.VOTE_COUNT
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesResponse(
    @SerialName( BACKDROPS) val backdrops: List<ImageResponse>,
    @SerialName( ID) val id: Int,
    @SerialName( POSTERS) val posters: List<ImageResponse>,
)

@Serializable
data class ImageResponse(
    @SerialName( ASPECT_RATIO) val aspectRatio: Double,
    @SerialName( FILE_PATH) val filePath: String,
    @SerialName( HEIGHT) val height: Int,
    @SerialName( ISO_639_1) val iso6391: String?,
    @SerialName( VOTE_AVERAGE) val voteAverage: Double,
    @SerialName( VOTE_COUNT) val voteCount: Int,
    @SerialName( WIDTH) val width: Int,
)

fun ImagesResponse.asDomainModel(): List<TMDbImage> = buildList {
    addAll(backdrops.map { TMDbImage(String.format(BASE_IMAGE_PATH, it.filePath), it.voteCount) })
    addAll(posters.map { TMDbImage(String.format(BASE_IMAGE_PATH, it.filePath), it.voteCount) })
    sortByDescending { it.voteCount }
}

private const val BACKDROPS = "backdrops"
private const val POSTERS = "posters"
private const val ASPECT_RATIO = "aspect_ratio"
private const val FILE_PATH = "file_path"
private const val HEIGHT = "height"
private const val ISO_639_1 = "iso_639_1"
private const val WIDTH = "width"
