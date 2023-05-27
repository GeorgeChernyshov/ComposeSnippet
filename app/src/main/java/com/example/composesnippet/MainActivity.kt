package com.example.composesnippet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.composesnippet.ui.composables.App
import com.example.composesnippet.ui.composables.FixturesListScreen
import com.example.composesnippet.ui.navigation.NavigationManager
import com.example.composesnippet.viewmodels.FixturesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { App(navManager) }
    }
}