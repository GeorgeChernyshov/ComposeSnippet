package com.example.composesnippet.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.composesnippet.data.repository.FixtureListRepository
import com.example.composesnippet.network.api.FootballApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val footballApi: FootballApi
) : ViewModel() {
    suspend fun testRequest() : String {
        val response = footballApi.getStatus()
        val status = response.response
        val account = status.account
        return account.firstname
    }
}