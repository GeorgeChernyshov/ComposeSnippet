package com.example.composesnippet.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composesnippet.ui.theme.ComposeSnippetTheme

@Composable
fun TournamentsListScreen() {
    ComposeSnippetTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Text(text = "Tournaments")
        }
    }
}