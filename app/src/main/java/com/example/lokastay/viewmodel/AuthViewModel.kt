package com.example.lokastay.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import com.example.lokastay.data.SessionManager
import com.example.lokastay.data.database.DatabaseProvider
import com.example.lokastay.data.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoggedIn: Boolean = false,
    val loggedInUserId: Int = -1,
    val message: String = ""
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseProvider.getDatabase(application)
    private val sessionManager = SessionManager(application)

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    init {
        checkLoginStatus()
    }

    fun checkLoginStatus() {
        val isLoggedIn = sessionManager.isLoggedIn()
        val userId = sessionManager.getLoggedInUserId()

        _uiState.value = AuthUiState(
            isLoggedIn = isLoggedIn,
            loggedInUserId = userId,
            message = if (isLoggedIn) "User sudah login" else "Belum login"
        )
    }

    fun login(email: String, password: String) {
        scope.launch {
            if (email.isBlank() || password.isBlank()) {
                _uiState.value = AuthUiState(
                    isLoggedIn = false,
                    loggedInUserId = -1,
                    message = "Email dan password wajib diisi"
                )
                return@launch
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _uiState.value = AuthUiState(
                    isLoggedIn = false,
                    loggedInUserId = -1,
                    message = "Format email tidak valid"
                )
                return@launch
            }

            val user = db.userDao().login(email, password)

            if (user != null) {
                sessionManager.saveLogin(user.id)
                _uiState.value = AuthUiState(
                    isLoggedIn = true,
                    loggedInUserId = user.id,
                    message = "Login berhasil"
                )
            } else {
                _uiState.value = AuthUiState(
                    isLoggedIn = false,
                    loggedInUserId = -1,
                    message = "Email atau password salah"
                )
            }
        }
    }

    fun register(name: String, email: String, phone: String, password: String) {
        scope.launch {
            if (name.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank()) {
                _uiState.value = AuthUiState(
                    isLoggedIn = false,
                    loggedInUserId = -1,
                    message = "Semua field wajib diisi"
                )
                return@launch
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _uiState.value = AuthUiState(
                    isLoggedIn = false,
                    loggedInUserId = -1,
                    message = "Format email tidak valid"
                )
                return@launch
            }

            if (password.length < 6) {
                _uiState.value = AuthUiState(
                    isLoggedIn = false,
                    loggedInUserId = -1,
                    message = "Password minimal 6 karakter"
                )
                return@launch
            }

            val existingUser = db.userDao().getUserByEmail(email)

            if (existingUser != null) {
                _uiState.value = AuthUiState(
                    isLoggedIn = false,
                    loggedInUserId = -1,
                    message = "Email sudah terdaftar"
                )
                return@launch
            }

            db.userDao().insert(
                User(
                    name = name,
                    email = email,
                    phone = phone,
                    password = password
                )
            )

            _uiState.value = AuthUiState(
                isLoggedIn = false,
                loggedInUserId = -1,
                message = "Register berhasil"
            )
        }
    }

    fun logout() {
        sessionManager.clearLogin()
        _uiState.value = AuthUiState(
            isLoggedIn = false,
            loggedInUserId = -1,
            message = "Logout berhasil"
        )
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
