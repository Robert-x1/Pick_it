package com.hitech.pickit.auth.domain.model

data class UserData(
    val userId: String,
    val username: String?,
    val email: String?,
    val profilePictureUrl: String?
)