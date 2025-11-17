package com.hitech.pickit.movie.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.movie.domain.use_case.GetAppThemeUseCase
import com.hitech.pickit.movie.domain.use_case.SetAppThemeUseCase
import com.hitech.pickit.movie.domain.util.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase
) : ViewModel() {

    /**
     * A [StateFlow] that emits the current theme mode (true for dark, false for light).
     *
     * We initialize it by calling the [getAppThemeUseCase] (which returns a Flow)
     * and converting that "cold" Flow into a "hot" [StateFlow] using `stateIn`.
     * This makes it efficient and safe to be collected by the UI.
     */
    val theme: StateFlow<AppTheme> = getAppThemeUseCase()
        .stateIn(
            scope = viewModelScope,
            // Eagerly start collecting so the value is ready when the UI needs it
            started = SharingStarted.Eagerly,
            // The default value to use before the flow emits its first value
            initialValue = AppTheme.LIGHT
        )


    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            setAppThemeUseCase(theme)
        }
    }
}