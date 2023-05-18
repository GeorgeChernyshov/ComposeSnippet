package com.example.composesnippet.network.model.fixtures.score

data class ScoreModel(
    val halftime: ScoreSliceModel,
    val fulltime: ScoreSliceModel,
    val extratime: ScoreSliceModel,
    val penalty: ScoreSliceModel
)