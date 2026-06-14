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
import com.example.lokastay.viewmodel.DetailViewModel

@Composable
fun DetailTestScreen(
    detailViewModel: DetailViewModel = viewModel()
) {
    val uiState by detailViewModel.uiState.collectAsStateWithLifecycle()

    var villaIdInput by remember { mutableStateOf("1") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Detail Test Lokastay")

        OutlinedTextField(
            value = villaIdInput,
            onValueChange = { villaIdInput = it },
            label = { Text("Villa ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val villaId = villaIdInput.toIntOrNull() ?: 1
                detailViewModel.loadDetail(villaId)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load Detail")
        }

        Text(text = uiState.message)

        uiState.villa?.let { villa ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = "ID: ${villa.id}")
                    Text(text = "Nama: ${villa.name}")
                    Text(text = "Lokasi: ${villa.location}")
                    Text(text = "Harga: ${villa.pricePerNight}")
                    Text(text = "Rating: ${villa.rating}")
                    Text(text = "Image URL: ${villa.imageUrl}")
                    Text(text = "Deskripsi: ${villa.description}")
                }
            }
        }

        Text(text = "Reviews:")

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.reviews) { review ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Username: ${review.username}")
                        Text(text = "Rating: ${review.rating}")
                        Text(text = "Comment: ${review.comment}")
                    }
                }
            }
        }
    }
}
