package com.hitech.pickit.movie.domain

import com.hitech.pickit.movie.presentation.models.Credit
import com.hitech.pickit.movie.presentation.models.Gender


class Cast(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: Int,
) : Credit


class Crew(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: String,
) : Credit
