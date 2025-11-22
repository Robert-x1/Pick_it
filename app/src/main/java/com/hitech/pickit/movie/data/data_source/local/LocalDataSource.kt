package com.hitech.pickit.movie.data.data_source.local


import com.hitech.pickit.movie.data.data_source.datastore.PreferenceDataStore
import com.hitech.pickit.movie.presentation.profile.util.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val preferenceDataStore: PreferenceDataStore
) {
    val themeMode: Flow<AppTheme>
        get() = preferenceDataStore.themeMode

    suspend fun saveThemePreference(theme: AppTheme) {
        preferenceDataStore.saveThemePreference(theme)
    }
}