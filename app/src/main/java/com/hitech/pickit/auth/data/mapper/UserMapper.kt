package com.hitech.pickit.auth.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.hitech.pickit.auth.domain.model.UserData

// FirebaseUser -> UserData
fun FirebaseUser.toDomainModel(): UserData {
    return UserData(
        userId = this.uid,
        username = this.displayName,
        email = this.email,
        profilePictureUrl = getHighResPhotoUrl(this.photoUrl?.toString())
    )
}

// gets the highest resolution possible for the user's pic
private fun getHighResPhotoUrl(originalUrl: String?): String? {
    if (originalUrl == null) return null
    return originalUrl.replace(Regex("=s\\d+(-c)?$"), "=s0")
}