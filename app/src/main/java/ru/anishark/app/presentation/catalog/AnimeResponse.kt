package ru.anishark.app.presentation.catalog

data class AnimeResponse(
    val data: List<Anime>
)

data class Anime(
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?
)