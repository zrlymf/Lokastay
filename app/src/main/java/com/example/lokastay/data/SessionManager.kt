package com.example.lokastay.data

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("lokastay_session", Context.MODE_PRIVATE)

    fun saveLogin(userId: Int) {
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .putInt("user_id", userId)
            .apply()
    }

    fun clearLogin() {
        prefs.edit()
            .putBoolean("is_logged_in", false)
            .remove("user_id")
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }

    fun getLoggedInUserId(): Int {
        return prefs.getInt("user_id", -1)
    }
}