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
import com.example.lokastay.viewmodel.FavoriteViewModel

@Composable
fun FavoriteTestScreen(
    favoriteViewModel: FavoriteViewModel = viewModel()
) {
    val uiState by favoriteViewModel.uiState.collectAsStateWithLifecycle()

    var userIdInput by remember { mutableStateOf("1") }
    var villaIdInput by remember { mutableStateOf("1") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Favorite Test Lokastay")

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

        Button(
            onClick = {
                favoriteViewModel.loadFavorites(
                    userId = userIdInput.toIntOrNull() ?: 1
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load Favorites")
        }

        Button(
            onClick = {
                favoriteViewModel.addFavorite(
                    userId = userIdInput.toIntOrNull() ?: 1,
                    villaId = villaIdInput.toIntOrNull() ?: 1
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Favorite")
        }

        Button(
            onClick = {
                favoriteViewModel.removeFavorite(
                    userId = userIdInput.toIntOrNull() ?: 1,
                    villaId = villaIdInput.toIntOrNull() ?: 1
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Remove Favorite")
        }

        Text(text = uiState.message)

        Text(text = "Favorite Villas:")

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.favoriteVillas) { villa ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
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
    }
}