package com.example.composesnippet.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composesnippet.R
import com.example.composesnippet.ui.theme.Shapes
import com.example.composesnippet.ui.uistate.fixtures.FixturesListItemTeamUiState
import com.example.composesnippet.ui.uistate.fixtures.FixturesListItemUiState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FixtureItem(
    uiState: FixturesListItemUiState,
    onItemClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .clickable { onItemClick.invoke(uiState.home.score) },
        shape = Shapes.medium,
        color = MaterialTheme.colors.surface
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
        ) {
            GlideImage(
                model = uiState.home.logo,
                contentDescription = LocalContext.current.getString(R.string.home_logo_description),
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )

            Column(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Row {
                    Text(
                        modifier = Modifier.weight(1f),
                        fontWeight = boldIfWinner(uiState.home),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = uiState.home.name,
                        textAlign = TextAlign.End
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = "-"
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        fontWeight = boldIfWinner(uiState.away),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = uiState.away.name
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = uiState.home.score.toString(),
                        fontWeight = boldIfWinner(uiState.home)
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = "-"
                    )

                    Text(
                        text = uiState.away.score.toString(),
                        fontWeight = boldIfWinner(uiState.away)
                    )
                }
            }

            GlideImage(
                model = uiState.away.logo,
                contentDescription = LocalContext.current.getString(R.string.away_logo_description),
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
        }
    }
}

fun boldIfWinner(uiState: FixturesListItemTeamUiState) =
    if (uiState.winner == true) FontWeight.Bold
    else FontWeight.Normal