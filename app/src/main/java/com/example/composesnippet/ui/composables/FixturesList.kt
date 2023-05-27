package com.example.composesnippet.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.uistate.fixtures.FixturesListUiState

@Composable
fun FixturesList(
    uiState: FixturesListUiState,
    onItemClick: (Int) -> Unit
) {
    ComposeSnippetTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn(Modifier.fillMaxWidth()) {
                items(uiState.fixtures) { item ->
                    FixtureItem(item, onItemClick)
                }
            }
        }
    }
}
