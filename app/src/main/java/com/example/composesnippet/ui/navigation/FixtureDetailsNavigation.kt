package com.example.composesnippet.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.composesnippet.ui.composables.Screen

object FixtureDetailsNavigation : NavigationCommand {

    const val FIXTURE_ID = "fixtureId"

    override val destination = "${Screen.Fixtures.route}/details/{$FIXTURE_ID}"
    override val arguments = listOf(navArgument(FIXTURE_ID) { type = NavType.IntType })

    fun details(fixtureId: Int) = object : NavigationCommand {
        override val arguments = FixtureDetailsNavigation.arguments

        override val destination = "${Screen.Fixtures.route}/details/$fixtureId"
    }
}