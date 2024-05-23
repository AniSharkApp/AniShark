package ru.anishark.app.domain.model

data class AnimeModel (
    val malId: Int,
    val title: String,
    val synopsis: String,
    val year: Int,
    val episodes: Int?,
    val imageUrl: String,
    val score: Double
)