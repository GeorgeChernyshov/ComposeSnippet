package com.example.composesnippet.ui.composables.fixtures

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.composesnippet.R
import com.example.composesnippet.ui.composables.AppBar
import com.example.composesnippet.ui.navigation.NavigationManager
import com.example.composesnippet.ui.navigation.UpNavigation
import com.example.composesnippet.ui.theme.ComposeSnippetTheme
import com.example.composesnippet.ui.uistate.common.AppBarUiState
import com.example.composesnippet.ui.uistate.fixtures.FixtureDetailsInfoUiState
import com.example.composesnippet.ui.uistate.fixtures.FixtureDetailsOverviewUiState
import com.example.composesnippet.ui.uistate.fixtures.FixtureDetailsUiState
import com.example.composesnippet.viewmodels.FixtureDetailsViewModel

@Composable
fun FixtureDetailsScreen(
    fixtureId: Int?,
    manager: NavigationManager,
    viewModel: FixtureDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    fixtureId?.also { viewModel.getFixture(it) }

    ComposeSnippetTheme {
        FixtureDetails(
            uiState = state,
            onBackPressed = { manager.navigate(UpNavigation) }
        )
    }
}

@Composable
fun FixtureDetails(
    uiState: FixtureDetailsUiState,
    onBackPressed: () -> Unit
) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AppBar(
                uiState = AppBarUiState("Fixture"),
                onBackPressed = onBackPressed
            )

            FixtureDetailsOverview(uiState = uiState.overview)
            FixtureDetailsInfo(uiState = uiState.info)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FixtureDetailsOverview(uiState: FixtureDetailsOverviewUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f),
                model = uiState.homeLogo,
                contentDescription = null
            )

            Text(
                text = uiState.homeScore.toString(),
                style = MaterialTheme.typography.h1,
                fontWeight = boldIfWinner(uiState.homeWinner)
            )

            Text(
                text = "-",
                style = MaterialTheme.typography.h1
            )

            Text(
                text = uiState.awayScore.toString(),
                style = MaterialTheme.typography.h1,
                fontWeight = boldIfWinner(uiState.awayWinner)
            )

            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f),
                model = uiState.awayLogo,
                contentDescription = null
            )
        }

        Row(Modifier.padding(top = 8.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                text = uiState.homeName,
                textAlign = TextAlign.End,
                fontWeight = boldIfWinner(uiState.homeWinner),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = "-",
            )

            Text(
                modifier = Modifier.weight(1f),
                text = uiState.awayName,
                fontWeight = boldIfWinner(uiState.awayWinner),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun FixtureDetailsInfoLine(
    @StringRes titleRes: Int,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = titleRes),
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .padding(start = 2.dp)
                .weight(1f),
            text = value
        )
    }
}

@Composable
fun FixtureDetailsInfo(uiState: FixtureDetailsInfoUiState) {
    Column {
        uiState.venueName?.let {
            FixtureDetailsInfoLine(
                titleRes = R.string.fixture_details_info_venue_name,
                value = it
            )
        }

        uiState.venueCity?.let {
            FixtureDetailsInfoLine(
                titleRes = R.string.fixture_details_info_venue_city,
                value = it
            )
        }

        uiState.referee?.let {
            FixtureDetailsInfoLine(
                titleRes = R.string.fixture_details_info_referee,
                value = it
            )
        }

        uiState.status?.let {
            FixtureDetailsInfoLine(
                titleRes = R.string.fixture_details_info_status,
                value = it
            )
        }
    }
}

fun boldIfWinner(winner: Boolean?) =
    if (winner == true) FontWeight.Bold
    else FontWeight.Normal

@Preview
@Composable
fun FixtureDetailsOverviewPreview() {
    ComposeSnippetTheme {
        FixtureDetailsOverview(uiState = previewUiState.overview)
    }
}

@Preview
@Composable
fun FixtureDetailsInfoLinePreview() {
    ComposeSnippetTheme {
        FixtureDetailsInfoLine(
            titleRes = R.string.fixture_details_info_venue_name,
            value = "Kashima Soccer Stadium"
        )
    }
}

@Preview
@Composable
fun FixtureDetailsInfoPreview() {
    ComposeSnippetTheme {
        FixtureDetailsInfo(uiState = previewUiState.info)
    }
}

@Preview
@Composable
fun FixtureDetailsPreview() {
    ComposeSnippetTheme {
        FixtureDetails(previewUiState) {}
    }
}

private val previewUiState = FixtureDetailsUiState(
    overview = FixtureDetailsOverviewUiState(
        homeName = "Tensung",
        homeScore = 1,
        homeWinner = true,
        homeLogo = "https://media-2.api-sports.io/football/teams/5590.png",
        awayName = "RTC",
        awayScore = 0,
        awayWinner = false,
        awayLogo = "https://media-1.api-sports.io/football/teams/19005.png"
    ),
    info = FixtureDetailsInfoUiState(
        venueName = "Kashima Soccer Stadium",
        venueCity = "Kashima",
        referee = null,
        status = "Not Started"
    )
)