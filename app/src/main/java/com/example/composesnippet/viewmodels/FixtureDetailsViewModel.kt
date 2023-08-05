package com.example.composesnippet.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesnippet.data.repository.FixtureDetailsRepository
import com.example.composesnippet.ui.uistate.fixtures.FixtureDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixtureDetailsViewModel @Inject constructor(
    private val fixtureDetailsRepository: FixtureDetailsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(FixtureDetailsUiState.DEFAULT)
    val state = _state.asStateFlow()

    fun getFixture(id: Int) = viewModelScope.launch {
        fixtureDetailsRepository.getFixtureDetails(id)?.let {
            _state.emit(FixtureDetailsUiState.from(it))
        }
    }
}