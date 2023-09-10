package com.example.composesnippet.ui.uistate.teams

data class TeamsListUiState(
    val teams: List<TeamsListItemUiState?>,
    val isLoading: Boolean,
    val loadingError: Boolean,
    val allElements: Boolean,
) {
    companion object {
        val DEFAULT = TeamsListUiState(
            teams = emptyList(),
            isLoading = true,
            loadingError = false,
            allElements = false
        )
    }
}