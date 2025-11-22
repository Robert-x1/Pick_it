package com.hitech.pickit.movie.domain.use_case.theme


import com.hitech.pickit.core.domain.repository.profile.ProfileRepository
import com.hitech.pickit.movie.presentation.profile.util.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppThemeUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): Flow<AppTheme> = repository.appTheme
}