package com.example.composesnippet.network.model.fixtures.teams

import java.net.URL

data class TeamModel(
    val id: Int,
    val name: String,
    val logo: URL,
    val winner: Boolean?
)