package com.example.composesnippet.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.composesnippet.ui.composables.common.Screen
import com.example.composesnippet.ui.composables.fixtures.FixtureDetailsScreen
import com.example.composesnippet.ui.composables.fixtures.FixturesListScreen
import com.example.composesnippet.ui.composables.teams.TeamsListScreen
import com.example.composesnippet.ui.navigation.FixtureDetailsNavigation
import com.example.composesnippet.ui.navigation.FixtureListNavigation
import com.example.composesnippet.ui.navigation.NavigationManager
import com.example.composesnippet.ui.navigation.UpNavigation
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.uistate.AppState

@Composable
fun App(navigationManager: NavigationManager) {
    ComposeSnippetTheme {
        val appState = rememberAppState()
        val items = listOf(
            Screen.Fixtures,
            Screen.Teams,
            Screen.Tournaments
        )

        Column(
            Modifier.fillMaxHeight()
                .fillMaxWidth()
        ) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = appState.navController,
                startDestination = Screen.Fixtures.route
            ) {
                navigation(
                    route = Screen.Fixtures.route,
                    startDestination = FixtureListNavigation.destination
                ) {
                    addFixturesGraph(navigationManager)
                }

                composable(Screen.Teams.route) { TeamsListScreen() }
                composable(Screen.Tournaments.route) { TournamentsListScreen() }
            }

            BottomNavigation {
                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {},
                        label = { Text(stringResource(id = screen.resourceId)) },
                        selected = currentDestination?.hierarchy
                            ?.any { it.route == screen.route }
                            ?: false,
                        onClick = {
                            appState.navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(appState.navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }

        navigationManager.commands.collectAsState().value.also { command ->
            if (command is UpNavigation) {
                appState.navController.navigateUp()
            } else command?.destination?.let {
                appState.navController.navigate(it)
            }
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember {
    AppState(navController)
}

//fun NavGraphBuilder.navGraph() {
//    navigation(
//        route = S,
//        startDestination = FixtureListNavigation.destination
//    ) {
//        addFixturesGraph()
//    }}

fun NavGraphBuilder.addFixturesGraph(manager: NavigationManager) {
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
        FixtureDetailsScreen(
            it.arguments?.getInt(FixtureDetailsNavigation.FIXTURE_ID),
            manager
        )
    }
}