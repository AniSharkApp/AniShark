package ru.anishark.app.domain.model

data class AnimeModel(
    val malId: Int,
    val title: String,
    val titleEnglish: String? = null,
    val synopsis: String? = null,
    val year: Int? = null,
    val episodes: Int? = null,
    val imageUrl: String? = null,
    val score: Double? = null,
    val season: String? = null,
    val studio: String? = null,
)
