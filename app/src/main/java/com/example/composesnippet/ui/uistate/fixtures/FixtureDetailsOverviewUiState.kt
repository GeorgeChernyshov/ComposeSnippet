package com.example.composesnippet.ui.uistate.fixtures

import java.net.URL

data class FixtureDetailsOverviewUiState(
    val homeName: String,
    val homeScore: Int,
    val homeWinner: Boolean?,
    val homeLogo: String?,
    val awayName: String,
    val awayScore: Int,
    val awayWinner: Boolean?,
    val awayLogo: String?
) {
    companion object {
        val DEFAULT = FixtureDetailsOverviewUiState(
            homeName = "",
            homeScore = 0,
            homeWinner = null,
            homeLogo = null,
            awayName = "",
            awayScore = 0,
            awayWinner = null,
            awayLogo = null
        )
    }
}