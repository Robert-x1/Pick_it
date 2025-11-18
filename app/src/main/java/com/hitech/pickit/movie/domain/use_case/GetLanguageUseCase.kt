package com.hitech.pickit.movie.domain.use_case

import com.hitech.pickit.movie.domain.repository.ProfileRepository
import com.hitech.pickit.movie.domain.util.AppLanguage
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): AppLanguage {
        return repository.getCurrentLanguage()
    }
}