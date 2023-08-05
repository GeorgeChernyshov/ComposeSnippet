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
import javax.inject.Inject

@HiltViewModel
class FixturesListViewModel @Inject constructor(
    private val fixtureListRepository: FixtureListRepository,
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val _state = MutableStateFlow(FixturesListUiState(emptyList()))
    val state: StateFlow<FixturesListUiState> = _state

    init {
        viewModelScope.launch { _state.emit(getFixtures()) }
    }

    suspend fun getFixtures(): FixturesListUiState {
        val items = fixtureListRepository.getFixtures()
            .map {
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

        return FixturesListUiState(items)
    }

    suspend fun getFirstFixture(): FixturesListItemUiState? {
        val model = fixtureListRepository.getFirstFixture()
        return model?.let {
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
    }

    fun navigateToDetailsScreen(fixtureId: Int) = navigationManager
        .navigate(FixtureDetailsNavigation.details(fixtureId))
}