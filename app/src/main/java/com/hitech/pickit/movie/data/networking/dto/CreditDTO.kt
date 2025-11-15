package com.hitech.pickit.movie.data.networking.dto


import com.hitech.pickit.movie.domain.Cast
import com.hitech.pickit.movie.domain.Crew
import com.hitech.pickit.movie.presentation.models.Gender
import com.hitech.pickit.movie.utili.Constants.BASE_WIDTH_342_PATH
import com.hitech.pickit.movie.utili.Constants.ID
import com.hitech.pickit.movie.utili.Constants.NAME
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastResponse(
    @SerialName( "character")
    val role: String,
    @SerialName( NAME)
    val name: String,
    @SerialName( PROFILE_PATH)
    val profilePath: String?,
    @SerialName( GENDER)
    val gender: Int,
    @SerialName( ID)
    val id: Int,
)

@Serializable
data class CrewResponse(
    @SerialName( "job")
    val role: String,
    @SerialName( NAME)
    val name: String,
    @SerialName( PROFILE_PATH)
    val profilePath: String?,
    @SerialName( GENDER)
    val gender: Int,
    @SerialName(ID)
    val id: String,
)

fun List<CastResponse>.asCastDomainModel() = map(CastResponse::asCastDomainModel)

fun List<CrewResponse>.asCrewDomainModel() = map(CrewResponse::asCrewDomainModel)

private fun CastResponse.asCastDomainModel(): Cast = Cast(
    role,
    name,
    profilePath?.let { profilePath ->
        String.format(
            BASE_WIDTH_342_PATH,
            profilePath,
        )
    },
    gender.toGender(),
    id,
)

private fun CrewResponse.asCrewDomainModel(): Crew = Crew(
    role,
    name,
    profilePath?.let { profilePath ->
        String.format(
            BASE_WIDTH_342_PATH,
            profilePath,
        )
    },
    gender.toGender(),
    id,
)

private fun Int.toGender() = if (this == 1) Gender.FEMALE else Gender.MALE

private const val PROFILE_PATH = "profile_path"
private const val GENDER = "gender"
