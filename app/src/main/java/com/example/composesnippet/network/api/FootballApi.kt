package com.example.composesnippet.network.api

import com.example.composesnippet.network.model.fixtures.FixturesResponse
import com.example.composesnippet.network.model.status.StatusModel
import com.example.composesnippet.network.model.status.StatusResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date

interface FootballApi {

    @GET("status")
    suspend fun getStatus(): StatusResponse

    @GET("fixtures")
    suspend fun getFixtures(
        @Query("ids") ids: String? = null,
        @Query("live") live: String? = null,
        @Query("date") date: Date? = null,
        @Query("league") league: Int? = null,
        @Query("season") season: Int? = null,
        @Query("team") team: Int? = null,
        @Query("last") last: Int? = null,
        @Query("next") next: Int? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("round") round: String? = null,
        @Query("venue") venue: Int? = null,
        @Query("timezone") timezone: String? = null
    ): FixturesResponse

    @GET("fixtures")
    suspend fun getFixtureDetails(
        @Query("id") id: Int,
    ): FixturesResponse
}