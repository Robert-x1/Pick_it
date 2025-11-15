package com.hitech.pickit.movie.data.networking.dto

import com.hitech.pickit.movie.domain.model.Person
import com.hitech.pickit.movie.utili.Constants
import com.hitech.pickit.movie.utili.Constants.ID
import com.hitech.pickit.movie.utili.Constants.NAME
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.String.format


@Serializable
data class PersonDTO(
    @SerialName( "birthday")
    val birthDay: String?,
    @SerialName( "deathday")
    val deathDay: String?,
    @SerialName( ID)
    val id: Int,
    @SerialName( NAME)
    val name: String,
    @SerialName( "biography")
    val biography: String,
    @SerialName( "place_of_birth")
    val placeOfBirth: String?,
    @SerialName( "profile_path")
    val profilePath: String?,
)

fun PersonDTO.asDomainModel(): Person = Person(
    birthDay,
    deathDay,
    id,
    name,
    biography,
    placeOfBirth,
    profilePath?.let { profilePath ->
        format(
            Constants.BASE_WIDTH_342_PATH,
            profilePath,
        )
    },
)
