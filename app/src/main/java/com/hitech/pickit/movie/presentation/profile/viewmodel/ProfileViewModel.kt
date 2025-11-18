package com.hitech.pickit.movie.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.movie.domain.use_case.GetAppThemeUseCase
import com.hitech.pickit.movie.domain.use_case.GetLanguageUseCase
import com.hitech.pickit.movie.domain.use_case.SetAppThemeUseCase
import com.hitech.pickit.movie.domain.use_case.SetLanguageUseCase
import com.hitech.pickit.movie.domain.util.AppLanguage
import com.hitech.pickit.movie.domain.util.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase
) : ViewModel() {

    val theme: StateFlow<AppTheme> = getAppThemeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = AppTheme.LIGHT
        )


    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            setAppThemeUseCase(theme)
        }
    }

    private val _currentLanguage = MutableStateFlow(getLanguageUseCase())
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    fun setLanguage(language: AppLanguage) {
        viewModelScope.launch {
            setLanguageUseCase(language)
            _currentLanguage.value = language
        }
    }
}