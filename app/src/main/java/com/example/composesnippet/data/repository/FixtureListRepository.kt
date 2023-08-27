package com.example.composesnippet.data.repository

import com.example.composesnippet.network.api.FootballApi
import com.example.composesnippet.network.model.fixtures.FixtureModel
import javax.inject.Inject

class FixtureListRepository @Inject constructor(
    private val footballApi: FootballApi
) {

    suspend fun getFixtures(date: String) : List<FixtureModel> {
        val a = footballApi.getFixtures(
            date = date,
        )
        return a.response.filter { it.league.id.mod(10) == 1 }
    }

//    suspend fun getFirstFixture() : FixtureModel? {
//        return getFixtures().firstOrNull()
//    }
}