package com.example.composesnippet.network.model.fixtures.league

import java.net.URL

data class LeagueModel(
    val id: Int,
    val name: String,
    val logo: URL,
    val flag: URL,
    val season: Int,
    val round: String
)