package com.example.lokastay.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lokastay.viewmodel.TransactionViewModel

@Composable
fun TransactionTestScreen(
    transactionViewModel: TransactionViewModel = viewModel()
) {
    val uiState by transactionViewModel.uiState.collectAsStateWithLifecycle()

    var userIdInput by remember { mutableStateOf("1") }
    var villaIdInput by remember { mutableStateOf("1") }
    var nightsInput by remember { mutableStateOf("2") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Transaction Test Lokastay")

        OutlinedTextField(
            value = userIdInput,
            onValueChange = { userIdInput = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = villaIdInput,
            onValueChange = { villaIdInput = it },
            label = { Text("Villa ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nightsInput,
            onValueChange = { nightsInput = it },
            label = { Text("Jumlah Malam") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                transactionViewModel.calculateTotalPrice(
                    villaId = villaIdInput.toIntOrNull() ?: 1,
                    nights = nightsInput.toIntOrNull() ?: 1
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung Total Harga")
        }

        Button(
            onClick = {
                transactionViewModel.createTransaction(
                    userId = userIdInput.toIntOrNull() ?: 1,
                    villaId = villaIdInput.toIntOrNull() ?: 1,
                    nights = nightsInput.toIntOrNull() ?: 1
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buat Transaksi")
        }

        Button(
            onClick = {
                transactionViewModel.loadUserTransactions(
                    userId = userIdInput.toIntOrNull() ?: 1
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load Riwayat Transaksi")
        }

        Text(text = "Total Harga: ${uiState.totalPrice}")
        Text(text = uiState.message)

        Text(text = "Riwayat Transaksi:")

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.transactions) { transaction ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Transaction ID: ${transaction.id}")
                        Text(text = "User ID: ${transaction.userId}")
                        Text(text = "Villa ID: ${transaction.villaId}")
                        Text(text = "Jumlah Malam: ${transaction.nights}")
                        Text(text = "Total Price: ${transaction.totalPrice}")
                        Text(text = "Payment Status: ${transaction.paymentStatus}")
                    }
                }
            }
        }
    }
}
