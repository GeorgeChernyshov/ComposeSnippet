package com.example.composesnippet.ui.navigation

import androidx.navigation.NamedNavArgument

object UpNavigation : NavigationCommand {
    override val arguments: List<NamedNavArgument> = emptyList()
    override val destination: String = "up"
}