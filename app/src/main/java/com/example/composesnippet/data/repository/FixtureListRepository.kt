package com.example.composesnippet.data.repository

import com.example.composesnippet.network.api.FootballApi
import com.example.composesnippet.network.model.fixtures.FixtureModel
import javax.inject.Inject

class FixtureListRepository @Inject constructor(
    private val footballApi: FootballApi
) {

    suspend fun getFixtures() : List<FixtureModel> {
        val a = footballApi.getFixtures(
            live = "all"
        )
        return a.response
    }

    suspend fun getFirstFixture() : FixtureModel? {
        return getFixtures().firstOrNull()
    }
}