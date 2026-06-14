package com.example.lokastay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lokastay.data.database.DatabaseProvider
import com.example.lokastay.data.entity.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class TransactionUiState(
    val transactions: List<Transaction> = emptyList(),
    val totalPrice: Double = 0.0,
    val message: String = ""
)

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseProvider.getDatabase(application)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState: StateFlow<TransactionUiState> = _uiState

    fun calculateTotalPrice(villaId: Int, nights: Int) {
        scope.launch {
            try {
                val villa = db.villaDao().getVillaById(villaId)
                val total = villa.pricePerNight * nights

                _uiState.value = _uiState.value.copy(
                    totalPrice = total,
                    message = "Total harga berhasil dihitung"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    totalPrice = 0.0,
                    message = "Villa tidak ditemukan"
                )
            }
        }
    }

    fun createTransaction(userId: Int, villaId: Int, nights: Int) {
        scope.launch {
            try {
                val villa = db.villaDao().getVillaById(villaId)
                val total = villa.pricePerNight * nights

                val paymentStatus = if (total <= 5000000.0) {
                    "SUCCESS"
                } else {
                    "FAILED"
                }

                val transaction = Transaction(
                    userId = userId,
                    villaId = villaId,
                    nights = nights,
                    totalPrice = total,
                    paymentStatus = paymentStatus
                )

                db.transactionDao().insert(transaction)

                val updatedTransactions = db.transactionDao().getUserTransactions(userId)

                _uiState.value = TransactionUiState(
                    transactions = updatedTransactions,
                    totalPrice = total,
                    message = "Transaksi dibuat dengan status: $paymentStatus"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    message = "Gagal membuat transaksi"
                )
            }
        }
    }

    fun loadUserTransactions(userId: Int) {
        scope.launch {
            val transactionList = db.transactionDao().getUserTransactions(userId)
            _uiState.value = _uiState.value.copy(
                transactions = transactionList,
                message = "Riwayat transaksi berhasil dimuat"
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}