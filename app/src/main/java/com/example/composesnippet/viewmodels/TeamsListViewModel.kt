package com.example.composesnippet.viewmodels

import androidx.lifecycle.ViewModel
import com.example.composesnippet.data.repository.TeamsListRepository
import com.example.composesnippet.ui.uistate.teams.TeamsListItemUiState
import com.example.composesnippet.ui.uistate.teams.TeamsListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TeamsListViewModel @Inject constructor(
    private val teamsListRepository: TeamsListRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TeamsListUiState.DEFAULT)
    val state = _state.asStateFlow()

    suspend fun reload() {
        _state.emit(state.value.copy(
            teams = emptyList(),
            isLoading = false,
            allElements = false
        ))

        getTeams()
    }

    suspend fun getTeams() {
        if (state.value.isLoading || state.value.allElements)
            return

        _state.emit(state.value.copy(
            isLoading = true
        ))

        val newItems = teamsListRepository.getTeams()
            .map {
                TeamsListItemUiState(
                    id = it.team.id,
                    name = it.team.name,
                    country = it.team.country,
                    image = it.team.logo
                )
            }

        _state.emit(state.value.copy(
            teams = state.value.teams + newItems,
            isLoading = false,
            allElements = newItems.isEmpty()
        ))
    }
}