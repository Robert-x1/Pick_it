package com.hitech.pickit.movie.domain.repository

import com.hitech.pickit.movie.domain.util.AppLanguage
import com.hitech.pickit.movie.domain.util.AppTheme
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    val appTheme: Flow<AppTheme>

    suspend fun saveThemePreference(theme: AppTheme)

    fun getCurrentLanguage(): AppLanguage

    suspend fun setLanguage(language: AppLanguage)
}
