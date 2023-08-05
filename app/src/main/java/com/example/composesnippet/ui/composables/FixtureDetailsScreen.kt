package com.example.composesnippet.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composesnippet.R
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.uistate.common.AppBarUiState
import com.example.composesnippet.ui.uistate.fixtures.FixtureDetailsTeamUiState
import com.example.composesnippet.ui.uistate.fixtures.FixtureDetailsUiState
import com.example.composesnippet.viewmodels.FixtureDetailsViewModel

@Composable
fun FixtureDetailsScreen(
    fixtureId: Int?,
    viewModel: FixtureDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    fixtureId?.also { viewModel.getFixture(it) }

    ComposeSnippetTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                AppBar(AppBarUiState("Fixture"))
                FixtureDetailsOverview(uiState = state)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FixtureDetailsOverview(uiState: FixtureDetailsUiState) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (
            homeLogo,
            homeName,
            topDelimeter,
            awayName,
            homeScore,
            bottomDelimeter,
            awayScore,
            awayLogo
        ) = createRefs()

        GlideImage(
            model = uiState.home.logo,
            contentDescription = LocalContext.current.getString(R.string.home_logo_description),
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .constrainAs(homeLogo) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            modifier = Modifier.constrainAs(homeName) {
                top.linkTo(parent.top)
                end.linkTo(topDelimeter.start, 4.dp)
            },
            text = uiState.home.name,
            fontWeight = boldIfWinner(uiState.home)
        )
        Text(
            modifier = Modifier.constrainAs(topDelimeter) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "-"
        )
        Text(
            modifier = Modifier.constrainAs(awayName) {
                top.linkTo(parent.top)
                start.linkTo(topDelimeter.end, 4.dp)
            },
            text = uiState.away.name,
            fontWeight = boldIfWinner(uiState.away)
        )
        Text(
            modifier = Modifier.constrainAs(homeScore) {
                top.linkTo(topDelimeter.bottom, 4.dp)
                end.linkTo(bottomDelimeter.start, 4.dp)
            },
            text = uiState.home.score.toString(),
            fontWeight = boldIfWinner(uiState.home)
        )
        Text(
            modifier = Modifier.constrainAs(bottomDelimeter) {
                top.linkTo(topDelimeter.bottom, 4.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "-"
        )
        Text(
            modifier = Modifier.constrainAs(awayScore) {
                top.linkTo(topDelimeter.bottom, 4.dp)
                start.linkTo(bottomDelimeter.end, 4.dp)
            },
            text = uiState.away.score.toString(),
            fontWeight = boldIfWinner(uiState.away)
        )
        GlideImage(
            model = uiState.away.logo,
            contentDescription = LocalContext.current.getString(R.string.away_logo_description),
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .constrainAs(awayLogo) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

fun boldIfWinner(uiState: FixtureDetailsTeamUiState) =
    if (uiState.winner == true) FontWeight.Bold
    else FontWeight.Normal