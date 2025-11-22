package com.hitech.pickit.core.domain.repository.profile

import com.hitech.pickit.movie.presentation.profile.util.AppLanguage
import com.hitech.pickit.movie.presentation.profile.util.AppTheme
import kotlinx.coroutines.flow.Flow
interface ProfileRepository {
    val appTheme: Flow<AppTheme>

    suspend fun saveThemePreference(theme: AppTheme)

    fun getCurrentLanguage(): AppLanguage

    suspend fun setLanguage(language: AppLanguage)
}