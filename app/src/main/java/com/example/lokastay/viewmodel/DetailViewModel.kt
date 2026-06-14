package com.example.lokastay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lokastay.data.database.DatabaseProvider
import com.example.lokastay.data.entity.Review
import com.example.lokastay.data.entity.Villa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DetailUiState(
    val villa: Villa? = null,
    val reviews: List<Review> = emptyList(),
    val message: String = ""
)

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseProvider.getDatabase(application)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    fun loadDetail(villaId: Int) {
        scope.launch {
            try {
                val villa = db.villaDao().getVillaById(villaId)
                val reviewList = db.reviewDao().getReviews(villaId)

                _uiState.value = DetailUiState(
                    villa = villa,
                    reviews = reviewList,
                    message = "Detail villa berhasil dimuat"
                )
            } catch (e: Exception) {
                _uiState.value = DetailUiState(
                    villa = null,
                    reviews = emptyList(),
                    message = "Villa tidak ditemukan"
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}