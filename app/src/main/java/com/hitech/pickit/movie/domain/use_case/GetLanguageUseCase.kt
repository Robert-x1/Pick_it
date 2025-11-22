package com.hitech.pickit.movie.domain.use_case


import com.hitech.pickit.core.domain.repository.profile.ProfileRepository
import com.hitech.pickit.movie.presentation.profile.util.AppLanguage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): AppLanguage {
        return repository.getCurrentLanguage()
    }
}