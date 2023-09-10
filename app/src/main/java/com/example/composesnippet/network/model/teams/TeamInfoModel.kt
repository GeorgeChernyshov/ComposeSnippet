package com.example.composesnippet.network.model.teams

data class TeamInfoModel(
    val id: Int,
    val name: String?,
    val code: String?,
    val country: String?,
    val founded: Int?,
    val national: Boolean?,
    val logo: String?
)