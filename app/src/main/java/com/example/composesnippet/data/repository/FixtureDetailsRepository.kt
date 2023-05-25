package com.example.composesnippet.data.repository

import com.example.composesnippet.network.api.FootballApi
import javax.inject.Inject

class FixtureDetailsRepository @Inject constructor(
    private val footballApi: FootballApi
) {
}