package com.example.composesnippet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.composesnippet.ui.composables.FixturesListScreen
import com.example.composesnippet.viewmodels.FixturesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: FixturesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycleScope.launch {
            val fixture = viewModel.getFirstFixture()
            setContent { FixturesListScreen(fixture) }
        }
    }
}