package com.example.composesnippet.ui.uistate.fixtures

import com.example.composesnippet.network.model.fixtures.FixtureModel

data class FixtureDetailsUiState(
    val home: FixtureDetailsTeamUiState,
    val away: FixtureDetailsTeamUiState
) {
    companion object {
        val DEFAULT = FixtureDetailsUiState(
            home = FixtureDetailsTeamUiState.DEFAULT,
            away = FixtureDetailsTeamUiState.DEFAULT
        )

        fun from(model: FixtureModel) = FixtureDetailsUiState(
            home = FixtureDetailsTeamUiState.from(model.teams.home, model.goals.home),
            away = FixtureDetailsTeamUiState.from(model.teams.away, model.goals.away)
        )
    }
}