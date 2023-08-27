package com.example.composesnippet.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesnippet.data.repository.FixtureListRepository
import com.example.composesnippet.ui.navigation.FixtureDetailsNavigation
import com.example.composesnippet.ui.navigation.FixtureListNavigation
import com.example.composesnippet.ui.navigation.NavigationManager
import com.example.composesnippet.ui.uistate.AppState
import com.example.composesnippet.ui.uistate.fixtures.FixturesListItemTeamUiState
import com.example.composesnippet.ui.uistate.fixtures.FixturesListItemUiState
import com.example.composesnippet.ui.uistate.fixtures.FixturesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toTimeUnit

@HiltViewModel
class FixturesListViewModel @Inject constructor(
    private val fixtureListRepository: FixtureListRepository,
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val _state = MutableStateFlow(FixturesListUiState.DEFAULT)
    val state: StateFlow<FixturesListUiState> = _state


    private var date = Date()

    suspend fun reload() {
        date = Date()

        _state.emit(state.value.copy(
            fixtures = emptyList(),
            isLoading = false,
            allElements = false
        ))

        getFixtures()
    }

    suspend fun getFixtures() {
        if (state.value.isLoading || state.value.allElements)
            return

        _state.emit(state.value.copy(
            isLoading = true
        ))

        val newItems = fixtureListRepository.getFixtures(
            SimpleDateFormat("yyyy-MM-dd", Locale.US)
                .format(date)
        ).map {
            FixturesListItemUiState(
                FixturesListItemTeamUiState(
                    it.teams.home.name,
                    it.goals.home,
                    it.teams.home.winner,
                    it.teams.home.logo
                ),
                FixturesListItemTeamUiState(
                    it.teams.away.name,
                    it.goals.away,
                    it.teams.away.winner,
                    it.teams.away.logo
                )
            )
        }

        if (newItems.isNotEmpty())
            date = Date(date.time - DurationUnit.DAYS.toTimeUnit().toMillis(1))

        _state.emit(state.value.copy(
            fixtures = state.value.fixtures + newItems,
            isLoading = false,
            allElements = newItems.isEmpty()
        ))
    }

//    suspend fun getFirstFixture(): FixturesListItemUiState? {
//        val model = fixtureListRepository.getFirstFixture()
//        return model?.let {
//            FixturesListItemUiState(
//                FixturesListItemTeamUiState(
//                    it.teams.home.name,
//                    it.goals.home,
//                    it.teams.home.winner,
//                    it.teams.home.logo
//                ),
//                FixturesListItemTeamUiState(
//                    it.teams.away.name,
//                    it.goals.away,
//                    it.teams.away.winner,
//                    it.teams.away.logo
//                )
//            )
//        }
//    }

    fun navigateToDetailsScreen(fixtureId: Int) = navigationManager
        .navigate(FixtureDetailsNavigation.details(fixtureId))
}