package com.example.lokastay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lokastay.ui.LokastayApp
import com.example.lokastay.ui.theme.LokastayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            LokastayTheme {
                LokastayApp()
            }
        }
    }
}