package com.example.composesnippet.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.composesnippet.data.repository.FixtureListRepository
import com.example.composesnippet.ui.uistate.fixtures.FixturesListItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FixturesListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val fixtureListRepository: FixtureListRepository,
) : ViewModel() {

    suspend fun getFirstFixture(): FixturesListItemUiState? {
        val model = fixtureListRepository.getFirstFixture()
        return model?.let {
            FixturesListItemUiState(
                it.teams.home.name,
                it.teams.away.name
            )
        }
    }
}