package com.hitech.pickit.movie.domain.use_case.theme



import com.hitech.pickit.core.domain.repository.profile.ProfileRepository
import com.hitech.pickit.movie.presentation.profile.util.AppTheme
import javax.inject.Inject

class SetAppThemeUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(theme: AppTheme) {
        repository.saveThemePreference(theme)
    }
}