package com.example.composesnippet.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composesnippet.ui.uistate.common.AppBarUiState
import com.example.composesnippet.viewmodels.AppBarViewModel

@Composable
fun AppBar(
    uiState: AppBarUiState,
    viewModel: AppBarViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier.background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clickable { viewModel.navigateUp() },
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "Back Arrow"
        )
        Text(text = uiState.title)
    }
}