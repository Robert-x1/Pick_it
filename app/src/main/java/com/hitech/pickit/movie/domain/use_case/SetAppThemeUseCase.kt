package com.hitech.pickit.movie.domain.use_case

import com.hitech.pickit.movie.domain.repository.ProfileRepository
import com.hitech.pickit.movie.domain.util.AppTheme
import javax.inject.Inject

class SetAppThemeUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(theme: AppTheme) {
        repository.saveThemePreference(theme)
    }
}