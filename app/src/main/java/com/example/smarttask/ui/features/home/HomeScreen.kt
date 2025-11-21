package com.example.smarttask.ui.features.home

import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onNavigateLogOut: () -> Unit
) {
    Surface() {
        Button(onClick = onNavigateLogOut) {
            Text(
                text = "Log Out"
            )
        }
    }
}
