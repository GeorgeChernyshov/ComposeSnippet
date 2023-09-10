package com.example.composesnippet.ui.uistate.fixtures

import com.example.composesnippet.network.model.fixtures.FixtureModel

data class FixtureDetailsUiState(
    val overview: FixtureDetailsOverviewUiState,
    val info: FixtureDetailsInfoUiState
) {
    companion object {
        val DEFAULT = FixtureDetailsUiState(
            overview = FixtureDetailsOverviewUiState.DEFAULT,
            info = FixtureDetailsInfoUiState.DEFAULT
        )

        fun from(model: FixtureModel) = FixtureDetailsUiState(
            overview = FixtureDetailsOverviewUiState(
                homeName = model.teams.home.name,
                homeScore = model.goals.home,
                homeWinner = model.teams.home.winner,
                homeLogo = model.teams.home.logo,
                awayName = model.teams.away.name,
                awayScore = model.goals.away,
                awayWinner = model.teams.away.winner,
                awayLogo = model.teams.away.logo
            ),
            info = FixtureDetailsInfoUiState(
                venueName = model.fixture.venue.name,
                venueCity = model.fixture.venue.city,
                referee = model.fixture.referee,
                status = model.fixture.status.long
            )
        )
    }
}