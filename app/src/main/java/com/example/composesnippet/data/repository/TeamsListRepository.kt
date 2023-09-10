package com.example.composesnippet.data.repository

import com.example.composesnippet.network.api.FootballApi
import javax.inject.Inject

class TeamsListRepository @Inject constructor(
    private val api: FootballApi
) {
    suspend fun getTeams() = api.getTeams(39).response
}