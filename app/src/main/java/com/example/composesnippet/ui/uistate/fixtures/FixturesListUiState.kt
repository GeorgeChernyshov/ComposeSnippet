package com.example.composesnippet.ui.uistate.fixtures

data class FixturesListUiState(
    val fixtures: List<FixturesListItemUiState?>,
    val isLoading: Boolean,
    val loadingError: Boolean,
    val allElements: Boolean,
) {
    companion object {
        val DEFAULT = FixturesListUiState(
            fixtures = emptyList(),
            isLoading = true,
            loadingError = false,
            allElements = false
        )
    }
}