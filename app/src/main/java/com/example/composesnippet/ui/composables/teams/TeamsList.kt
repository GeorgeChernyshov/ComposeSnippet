package com.example.composesnippet.ui.composables.teams

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.example.composesnippet.ui.composables.common.LoaderItem
import com.example.composesnippet.ui.composables.fixtures.FixtureItem
import com.example.composesnippet.ui.composables.fixtures.OnBottomReached
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.uistate.teams.TeamsListUiState

@Composable
fun TeamsList(
    uiState: TeamsListUiState,
    onScrolled: () -> Unit
) {
    ComposeSnippetTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val listState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = listState
            ) {
                val items = if (uiState.isLoading)
                    uiState.teams + null
                else uiState.teams

                items(items) { item ->
                    item?.let {
                        TeamItem(it)
                    } ?: LoaderItem()
                }
            }

            // call the extension function
            listState.OnBottomReached {
                // do on load more
                onScrolled.invoke()
            }
        }
    }
}

@Composable
fun LazyListState.OnBottomReached(
    lastItems: Int = 3,
    loadMore : () -> Unit
) {
    // state object which tells us if we should load more
    val shouldLoadMore = remember {
        derivedStateOf {
            // get last visible item
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            // Check if last visible item is the last item in the list
            lastVisibleItem.index == layoutInfo.totalItemsCount - (lastItems + 1)
        }
    }

    // Convert the state into a cold flow and collect
    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .collect {
                // if should load more, then invoke loadMore
                if (it) loadMore()
            }
    }
}