package com.example.lokastay.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TestHubScreen() {
    var currentScreen by remember { mutableStateOf("menu") }

    when (currentScreen) {
        "menu" -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "Lokastay Test Hub")

                Button(
                    onClick = { currentScreen = "login" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login Test")
                }

                Button(
                    onClick = { currentScreen = "register" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register Test")
                }

                Button(
                    onClick = { currentScreen = "villa" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Villa List Test")
                }

                Button(
                    onClick = { currentScreen = "detail" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Detail Test")
                }

                Button(
                    onClick = { currentScreen = "favorite" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Favorite Test")
                }

                Button(
                    onClick = { currentScreen = "transaction" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Transaction Test")
                }

                Button(
                    onClick = { currentScreen = "dashboard" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Dashboard Test")
                }
            }
        }

        "login" -> ScreenContainer(onBack = { currentScreen = "menu" }) {
            LoginTestScreen()
        }

        "register" -> ScreenContainer(onBack = { currentScreen = "menu" }) {
            RegisterTestScreen()
        }

        "villa" -> ScreenContainer(onBack = { currentScreen = "menu" }) {
            VillaListTestScreen()
        }

        "detail" -> ScreenContainer(onBack = { currentScreen = "menu" }) {
            DetailTestScreen()
        }

        "favorite" -> ScreenContainer(onBack = { currentScreen = "menu" }) {
            FavoriteTestScreen()
        }

        "transaction" -> ScreenContainer(onBack = { currentScreen = "menu" }) {
            TransactionTestScreen()
        }

        "dashboard" -> ScreenContainer(onBack = { currentScreen = "menu" }) {
            DashboardTestScreen()
        }
    }
}

@Composable
fun ScreenContainer(
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(16.dp)) {
            Button(onClick = onBack) {
                Text("Back to Menu")
            }
        }
        content()
    }
}