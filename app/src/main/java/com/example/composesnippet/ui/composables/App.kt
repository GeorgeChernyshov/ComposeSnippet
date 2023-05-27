package com.example.composesnippet.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composesnippet.ui.navigation.Destinations
import com.example.composesnippet.ui.navigation.FixtureDetailsNavigation
import com.example.composesnippet.ui.navigation.FixtureListNavigation
import com.example.composesnippet.ui.navigation.NavigationManager
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.uistate.AppState

@Composable
fun App(navigationManager: NavigationManager) {
    ComposeSnippetTheme {
        val appState = rememberAppState()

        NavHost(
            navController = appState.navController,
            startDestination = Destinations.FIXTURES_ROUTE
        ) {
            navGraph()
        }

        navigationManager.commands.collectAsState().value.also {
            if (it.destination.isNotEmpty())
                appState.navController.navigate(it.destination)
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember {
    AppState(navController)
}

fun NavGraphBuilder.navGraph() {
    navigation(
        route = Destinations.FIXTURES_ROUTE,
        startDestination = FixtureListNavigation.destination
    ) {
        addFixturesGraph()
    }}

fun NavGraphBuilder.addFixturesGraph() {
    composable(
        FixtureListNavigation.destination,
        FixtureListNavigation.arguments
    ) {
        FixturesListScreen()
    }
    composable(
        FixtureDetailsNavigation.destination,
        FixtureDetailsNavigation.arguments
    ) {
        FixtureDetailsScreen(it.arguments?.getInt(FixtureDetailsNavigation.FIXTURE_ID))
    }
}