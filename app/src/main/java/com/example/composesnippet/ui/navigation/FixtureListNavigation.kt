package com.example.composesnippet.ui.navigation

import androidx.navigation.NamedNavArgument

object FixtureListNavigation : NavigationCommand {
    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "${Destinations.FIXTURES_ROUTE}/list"
}