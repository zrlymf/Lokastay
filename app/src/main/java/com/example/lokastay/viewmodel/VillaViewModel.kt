package com.example.lokastay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lokastay.data.database.DatabaseProvider
import com.example.lokastay.data.entity.Villa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class VillaUiState(
    val villas: List<Villa> = emptyList(),
    val message: String = ""
)

class VillaViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseProvider.getDatabase(application)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _uiState = MutableStateFlow(VillaUiState())
    val uiState: StateFlow<VillaUiState> = _uiState

    init {
        loadAllVillas()
    }

    fun loadAllVillas() {
        scope.launch {
            val villaList = db.villaDao().getAllVillas()
            _uiState.value = VillaUiState(
                villas = villaList,
                message = "Data villa berhasil dimuat"
            )
        }
    }

    fun filterVilla(
        location: String,
        rating: Float,
        minPrice: Double,
        maxPrice: Double
    ) {
        scope.launch {
            val result = db.villaDao().filterVilla(
                location = location,
                rating = rating,
                minPrice = minPrice,
                maxPrice = maxPrice
            )
            _uiState.value = VillaUiState(
                villas = result,
                message = "Jumlah villa ditemukan: ${result.size}"
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}