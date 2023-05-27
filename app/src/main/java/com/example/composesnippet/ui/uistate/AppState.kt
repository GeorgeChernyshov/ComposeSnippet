package com.example.composesnippet.ui.uistate

import androidx.compose.runtime.Stable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Stable
class AppState(val navController: NavHostController) {

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    // Клик по навигационному меню, вкладке.
    fun navigateToRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                //Возвращаем выбранный экран,
                //иначе если backstack не пустой то показываем ранее открытое состяние
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    private fun NavBackStackEntry.lifecycleIsResumed() =
        this.lifecycle.currentState == Lifecycle.State.RESUMED

    private val NavGraph.startDestination: NavDestination?
        get() = findNode(startDestinationId)

    private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
        return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
    }

//    fun navigateToFixtureDetails(id: Int, from: NavBackStackEntry) {
//        if (from.lifecycleIsResumed()) {
//            navigateModel(
//                route = FixtureSections.FIXTURE_DETAILS.route,
//                model = id
//            )
//        }
//    }

    inline fun <reified T : Any> navigateModel(route: String, model: T) {
        val json = Json.encodeToString(model)
        navController.navigate("$route/$json")
    }
}