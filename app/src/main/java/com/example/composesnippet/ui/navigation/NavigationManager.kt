package com.example.composesnippet.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {
    var commands = MutableStateFlow<NavigationCommand>(FixtureListNavigation)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}