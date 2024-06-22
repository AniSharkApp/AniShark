package ru.anishark.app.domain.model

enum class AnimeRatingModel(
    val backingName: String,
    val displayName: String,
) {
    G("g", "All Ages"),
    PG("pg", "Children"),
    PG13("pg13", "13+"),
    R17("r17", "17+"),
    R("r", "Mild Nudity"),
    RX("rx", "Hentai"),
    ;

    companion object {
        fun getByDisplayName(displayName: String): AnimeRatingModel? =
            when (displayName) {
                G.displayName -> G
                PG.displayName -> PG
                PG13.displayName -> PG13
                R17.displayName -> R17
                R.displayName -> R
                RX.displayName -> RX
                else -> null
            }
    }
}
