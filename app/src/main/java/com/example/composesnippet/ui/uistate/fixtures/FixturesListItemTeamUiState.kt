package com.example.composesnippet.ui.uistate.fixtures

import java.net.URL

data class FixturesListItemTeamUiState(
    val name: String,
    val score: Int,
    val winner: Boolean?,
    val logo: URL
)