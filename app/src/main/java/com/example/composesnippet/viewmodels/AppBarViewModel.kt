package com.example.composesnippet.viewmodels

import androidx.lifecycle.ViewModel
import com.example.composesnippet.ui.navigation.NavigationManager
import com.example.composesnippet.ui.navigation.UpNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppBarViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun navigateUp() = navigationManager.navigate(UpNavigation)
}