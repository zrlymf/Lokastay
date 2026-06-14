package com.example.lokastay.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun login(email: String, pass: String) {
        _authState.value = AuthState.Success()
    }

    fun register(name: String, email: String, phone: String, pass: String) {
        _authState.value = AuthState.Success()
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}