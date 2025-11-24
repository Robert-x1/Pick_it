package com.hitech.pickit.auth.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.auth.domain.model.UserData
import com.hitech.pickit.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: Result<UserData>) {
        if (result.isSuccess) {
            _state.update { it.copy(
                isSignInSuccessful = true,
                isLoading = false
            ) }
        } else {
            _state.update { it.copy(
                isSignInSuccessful = false,
                signInError = result.exceptionOrNull()?.message,
                isLoading = false
            ) }
        }
    }

    fun signInWithGoogle(context: Context) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = authRepository.signInWithGoogle(context)
            onSignInResult(result)
        }
    }
}