package com.example.composesnippet.ui.uistate.fixtures

data class FixtureDetailsInfoUiState(
    val venueName: String?,
    val venueCity: String?,
    val referee: String?,
    val status: String?
) {
    companion object {
        val DEFAULT = FixtureDetailsInfoUiState(
            venueName = null,
            venueCity = null,
            referee = null,
            status = null
        )
    }
}