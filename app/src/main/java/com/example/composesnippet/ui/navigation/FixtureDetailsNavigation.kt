package com.example.composesnippet.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object FixtureDetailsNavigation : NavigationCommand {

    const val FIXTURE_ID = "fixtureId"

    override val destination = "${Destinations.FIXTURES_ROUTE}/details/{$FIXTURE_ID}"
    override val arguments = listOf(navArgument(FIXTURE_ID) { type = NavType.IntType })

//    fun dashboard(
//        userId: String? = null
//    ) = object : NavigationCommand {
//
//        override val arguments = arguments
//
//        override val destination = "dashboard/$userId"
//    }
//
    fun details(fixtureId: Int) = object : NavigationCommand {
        override val arguments = FixtureDetailsNavigation.arguments

        override val destination = "${Destinations.FIXTURES_ROUTE}/details/$fixtureId"
    }
}