package com.hitech.pickit.movie.domain.use_case


import com.hitech.pickit.core.domain.repository.profile.ProfileRepository
import com.hitech.pickit.movie.presentation.profile.util.AppLanguage
import javax.inject.Inject

class SetLanguageUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(language: AppLanguage) {
        repository.setLanguage(language)
    }
}