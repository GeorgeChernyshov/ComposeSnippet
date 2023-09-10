package com.example.composesnippet.network.model.teams

data class TeamVenueModel(
    val id: Int,
    val name: String?,
    val address: String?,
    val city: String?,
    val capacity: Int?,
    val surface: String?,
    val image: String?
)