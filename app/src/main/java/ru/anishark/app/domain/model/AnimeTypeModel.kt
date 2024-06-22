package ru.anishark.app.domain.model

enum class AnimeTypeModel(
    val backingName: String,
    val displayName: String,
) {
    TV("tv", "TV"),
    MOVIE("movie", "Movie"),
    OVA("ova", "OVA"),
    SPECIAL("special", "Special"),
    ONA("ona", "ONA"),
    MUSIC("music", "Music"),
    CM("cm", "CM"),
    PV("pv", "PV"),
    TV_SPECIAL("tv_special", "TV Special"),
}
