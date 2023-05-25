package com.example.composesnippet.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.composesnippet.data.repository.FixtureDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FixtureDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val fixtureDetailsRepository: FixtureDetailsRepository,
) : ViewModel() {
}