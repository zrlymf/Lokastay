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
import com.example.lokastay.viewmodel.DashboardViewModel

@Composable
fun DashboardTestScreen(
    dashboardViewModel: DashboardViewModel = viewModel()
) {
    val uiState by dashboardViewModel.uiState.collectAsStateWithLifecycle()

    var userIdInput by remember { mutableStateOf("1") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Dashboard Test Lokastay")

        OutlinedTextField(
            value = userIdInput,
            onValueChange = { userIdInput = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                dashboardViewModel.loadDashboard(
                    userId = userIdInput.toIntOrNull() ?: 1
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load Dashboard")
        }

        Text(text = uiState.message)

        Text(text = "Favorite Villas:")
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.favoriteVillas) { villa ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "ID: ${villa.id}")
                        Text(text = "Nama: ${villa.name}")
                        Text(text = "Lokasi: ${villa.location}")
                        Text(text = "Harga: ${villa.pricePerNight}")
                        Text(text = "Rating: ${villa.rating}")
                    }
                }
            }
        }

        Text(text = "Success Transactions:")
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.successTransactions) { transaction ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "ID: ${transaction.id}")
                        Text(text = "Villa ID: ${transaction.villaId}")
                        Text(text = "Nights: ${transaction.nights}")
                        Text(text = "Total: ${transaction.totalPrice}")
                        Text(text = "Status: ${transaction.paymentStatus}")
                    }
                }
            }
        }

        Text(text = "Failed Transactions:")
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.failedTransactions) { transaction ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "ID: ${transaction.id}")
                        Text(text = "Villa ID: ${transaction.villaId}")
                        Text(text = "Nights: ${transaction.nights}")
                        Text(text = "Total: ${transaction.totalPrice}")
                        Text(text = "Status: ${transaction.paymentStatus}")
                    }
                }
            }
        }
    }
}