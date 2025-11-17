package com.hitech.pickit.movie.domain.use_case

import com.hitech.pickit.movie.domain.repository.ProfileRepository
import com.hitech.pickit.movie.domain.util.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppThemeUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): Flow<AppTheme> = repository.appTheme
}