package com.example.composesnippet.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composesnippet.ui.navigation.Destinations
import com.example.composesnippet.ui.navigation.FixtureSections
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.uistate.AppState

@Composable
fun App() {
    ComposeSnippetTheme {
        val appState = rememberAppState()

        NavHost(
            navController = appState.navController,
            startDestination = Destinations.FIXTURES_ROUTE
        ) {
            navGraph()
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
        startDestination = FixtureSections.FIXTURES_LIST.route
    ) {
        addFixturesGraph()
    }}

fun NavGraphBuilder.addFixturesGraph(
    modifier: Modifier = Modifier
) {
    composable(FixtureSections.FIXTURES_LIST.route) {
        FixturesListScreen()
    }
    composable(FixtureSections.FIXTURE_DETAILS.route) {
        FixtureDetailsScreen()
    }
}