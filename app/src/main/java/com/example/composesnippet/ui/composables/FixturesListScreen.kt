package com.example.composesnippet.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.uistate.fixtures.FixturesListItemUiState

@Composable
fun FixturesListScreen(uiState: FixturesListItemUiState?) {
    ComposeSnippetTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            uiState?.let {
                Column(Modifier.fillMaxWidth()) {
                    FixtureItem(it)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSnippetTheme {
        Greeting("Android")
    }
}