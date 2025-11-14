package com.hitech.pickit.movie.presentation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman
import androidx.compose.ui.graphics.vector.ImageVector

enum class Gender { MALE, FEMALE }

val Gender.placeholderIcon: ImageVector
    get() = when (this) {
        Gender.MALE -> Icons.Rounded.Man
        Gender.FEMALE -> Icons.Rounded.Woman
    }

data class Actor(
    val id: Int,
    val role: String,
    val name: String,
    val profileUrl: String?,
    val gender: Gender
)

