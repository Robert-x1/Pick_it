package com.hitech.pickit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.auth.domain.repository.AuthRepository
import com.hitech.pickit.core.data.source.local.DataStoreRepository
import com.hitech.pickit.movie.utili.Constants.ONBOARDING_ROUTE
import com.hitech.pickit.movie.utili.MainDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow(ONBOARDING_ROUTE)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState().collect { completed ->

                val isLoggedIn = authRepository.getSignedInUser() != null

                _startDestination.value = when {
                    !completed -> ONBOARDING_ROUTE
                    isLoggedIn -> MainDestinations.HOME_ROUTE
                    else -> MainDestinations.SIGNIN_ROUTE
                }

                _isLoading.value = false
            }
        }
    }
    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.saveOnBoardingState(completed)
        }
    }
}