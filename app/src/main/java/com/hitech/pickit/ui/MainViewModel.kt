package com.hitech.pickit.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow(ONBOARDING_ROUTE)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->

                // check login
                val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
                val isLoggedIn = currentUser != null

                _startDestination.value = when {
                    !completed -> ONBOARDING_ROUTE // first install -> onBoarding
                    isLoggedIn -> MainDestinations.HOME_ROUTE // Logged in? -> go home
                    else -> MainDestinations.SIGNIN_ROUTE     // Not logged in? -> Go to Sign In (being a guest counts as not logged in)
                }

                _isLoading.value = false
            }
        }
    }

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch {
            repository.saveOnBoardingState(completed)
        }
    }
}