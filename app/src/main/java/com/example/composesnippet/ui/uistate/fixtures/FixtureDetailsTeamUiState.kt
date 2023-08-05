package com.example.composesnippet.ui.uistate.fixtures

import com.example.composesnippet.network.model.fixtures.teams.TeamModel
import java.net.URL

data class FixtureDetailsTeamUiState(
    val name: String,
    val score: Int,
    val winner: Boolean?,
    val logo: URL?
) {
    companion object {
        val DEFAULT = FixtureDetailsTeamUiState(
            name = "",
            score = 0,
            winner = null,
            logo = null
        )

        fun from(model: TeamModel, score: Int) = FixtureDetailsTeamUiState(
            name = model.name,
            score = score,
            winner = model.winner,
            logo = model.logo
        )
    }
}