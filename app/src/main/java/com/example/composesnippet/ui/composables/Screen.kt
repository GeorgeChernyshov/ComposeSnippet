package com.example.composesnippet.ui.composables

import androidx.annotation.StringRes
import com.example.composesnippet.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Fixtures : Screen("fixtures", R.string.label_fixtures)
    object Teams : Screen("teams", R.string.label_teams)
    object Tournaments : Screen("tournaments", R.string.label_tournaments)
}