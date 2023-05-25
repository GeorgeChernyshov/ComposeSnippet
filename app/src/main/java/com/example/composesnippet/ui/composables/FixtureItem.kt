package com.example.composesnippet.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
fun FixtureItem(uiState: FixturesListItemUiState) {
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        shape = Shapes.medium,
        color = MaterialTheme.colors.surface
    ) {
        ConstraintLayout(
            Modifier.fillMaxWidth().padding(16.dp, 8.dp)
        ) {
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
                modifier = Modifier.width(40.dp)
                    .height(40.dp)
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
                modifier = Modifier.width(40.dp)
                    .height(40.dp)
                    .constrainAs(awayLogo) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

fun boldIfWinner(uiState: FixturesListItemTeamUiState) =
    if (uiState.winner == true) FontWeight.Bold
    else FontWeight.Normal