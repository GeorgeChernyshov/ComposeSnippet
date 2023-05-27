package com.example.composesnippet.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.viewmodels.FixturesListViewModel

@Composable
fun FixturesListScreen(
    viewModel: FixturesListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    FixturesList(uiState = state) {
        viewModel.navigateToDetailsScreen(it)
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