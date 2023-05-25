package com.example.composesnippet.ui.navigation

import com.example.composesnippet.ui.navigation.Destinations.FIXTURES_ROUTE

object Destinations {
    const val FIXTURES_ROUTE = "fixtures"
}

enum class FixtureSections(val route: String) {
    FIXTURES_LIST("$FIXTURES_ROUTE/list"),
    FIXTURE_DETAILS("$FIXTURES_ROUTE/details")
}