package com.example.composesnippet.ui.navigation

import androidx.navigation.NamedNavArgument
import com.example.composesnippet.ui.composables.Screen

object FixtureListNavigation : NavigationCommand {
    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "${Screen.Fixtures.route}/list"
}