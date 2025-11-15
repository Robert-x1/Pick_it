package com.hitech.pickit.movie.domain.model

import com.hitech.pickit.movie.presentation.models.TMDbItem


class DetailWrapper(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val details: TMDbItemDetails,
    val images: List<TMDbImage>,
    val similarItems: List<TMDbItem>,
)
