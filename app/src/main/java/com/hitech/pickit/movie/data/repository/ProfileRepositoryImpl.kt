package com.hitech.pickit.movie.data.repository

import com.hitech.pickit.movie.data.data_source.local.LocalDataSource
import com.hitech.pickit.movie.domain.repository.ProfileRepository
import com.hitech.pickit.movie.domain.util.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): ProfileRepository {
    override val appTheme: Flow<AppTheme>
        get() = localDataSource.themeMode

    override suspend fun saveThemePreference(theme: AppTheme) {
        localDataSource.saveThemePreference(theme)
    }
}