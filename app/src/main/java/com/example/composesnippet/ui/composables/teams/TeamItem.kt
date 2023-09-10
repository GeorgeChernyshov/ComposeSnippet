package com.example.composesnippet.ui.composables.teams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composesnippet.R
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.theme.Shapes
import com.example.composesnippet.ui.uistate.teams.TeamsListItemUiState

@Composable
fun TeamItem(uiState: TeamsListItemUiState) {
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        shape = Shapes.medium,
        color = MaterialTheme.colors.surface
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
        ) {
            AsyncImage(
                model = uiState.image,
                contentDescription = LocalContext.current.getString(R.string.team_logo_description),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            )

            Column(
                Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = uiState.name.orEmpty(),
                    style = MaterialTheme.typography.h6
                )

                Text(text = uiState.country.orEmpty())
            }
        }
    }
}

val previewUiState = TeamsListItemUiState(
    id = 1,
    name = "Tensung",
    country = "Bhutan",
    image = "https://media-2.api-sports.io/football/teams/5590.png"
)

@Preview
@Composable
fun TeamItemPreview() {
    ComposeSnippetTheme {
        TeamItem(previewUiState)
    }
}