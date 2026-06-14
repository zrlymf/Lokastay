package com.example.lokastay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lokastay.data.database.DatabaseProvider
import com.example.lokastay.data.entity.Favorite
import com.example.lokastay.data.entity.Villa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class FavoriteUiState(
    val favorites: List<Favorite> = emptyList(),
    val favoriteVillas: List<Villa> = emptyList(),
    val message: String = ""
)

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseProvider.getDatabase(application)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState

    fun loadFavorites(userId: Int) {
        scope.launch {
            val favoriteList = db.favoriteDao().getFavorites(userId)
            val villaList = favoriteList.mapNotNull { favorite ->
                try {
                    db.villaDao().getVillaById(favorite.villaId)
                } catch (e: Exception) {
                    null
                }
            }

            _uiState.value = FavoriteUiState(
                favorites = favoriteList,
                favoriteVillas = villaList,
                message = "Favorite berhasil dimuat"
            )
        }
    }

    fun addFavorite(userId: Int, villaId: Int) {
        scope.launch {
            db.favoriteDao().addFavorite(Favorite(userId = userId, villaId = villaId))
            loadFavorites(userId)
        }
    }

    fun removeFavorite(userId: Int, villaId: Int) {
        scope.launch {
            db.favoriteDao().removeFavorite(Favorite(userId = userId, villaId = villaId))
            loadFavorites(userId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}