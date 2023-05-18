package com.example.composesnippet.network.model.fixtures

import com.example.composesnippet.network.model.fixtures.goals.GoalsModel
import com.example.composesnippet.network.model.fixtures.info.FixtureInfoModel
import com.example.composesnippet.network.model.fixtures.league.LeagueModel
import com.example.composesnippet.network.model.fixtures.score.ScoreModel
import com.example.composesnippet.network.model.fixtures.teams.TeamsModel

data class FixtureModel(
    val fixture: FixtureInfoModel,
    val league: LeagueModel,
    val teams: TeamsModel,
    val goals: GoalsModel,
    val score: ScoreModel
)