package com.example.composesnippet.network.model.fixtures.info

import java.util.Date

data class FixtureInfoModel(
    val id: Int,
    val referee: String?,
    val timezone: String,
    val date: Date,
    val timestamp: Long,
    val periods: PeriodsModel,
    val venue: VenueModel,
    val status: FixtureStatusModel
)