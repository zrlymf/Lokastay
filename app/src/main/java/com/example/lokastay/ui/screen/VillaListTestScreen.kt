package com.example.lokastay.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.example.lokastay.viewmodel.VillaViewModel

@Composable
fun VillaListTestScreen(
    villaViewModel: VillaViewModel = viewModel()
) {
    val uiState by villaViewModel.uiState.collectAsStateWithLifecycle()

    var location by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("4.5") }
    var minPrice by remember { mutableStateOf("800000") }
    var maxPrice by remember { mutableStateOf("2000000") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Villa List Test Lokastay")

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Lokasi") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Min Rating") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = minPrice,
            onValueChange = { minPrice = it },
            label = { Text("Min Price") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = maxPrice,
            onValueChange = { maxPrice = it },
            label = { Text("Max Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                villaViewModel.filterVilla(
                    location = location,
                    rating = rating.toFloatOrNull() ?: 0f,
                    minPrice = minPrice.toDoubleOrNull() ?: 0.0,
                    maxPrice = maxPrice.toDoubleOrNull() ?: Double.MAX_VALUE
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Filter Villa")
        }

        Button(
            onClick = {
                villaViewModel.loadAllVillas()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load All Villas")
        }

        Text(text = uiState.message)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.villas) { villa ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Nama: ${villa.name}")
                        Text(text = "Lokasi: ${villa.location}")
                        Text(text = "Harga: ${villa.pricePerNight}")
                        Text(text = "Rating: ${villa.rating}")
                        Text(text = "Deskripsi: ${villa.description}")
                    }
                }
            }
        }
    }
}