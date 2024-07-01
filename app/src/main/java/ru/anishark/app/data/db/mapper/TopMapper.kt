package ru.anishark.app.data.db.mapper

import ru.anishark.app.data.db.dbo.TopAnimeDBO
import ru.anishark.app.domain.model.AnimeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopMapper @Inject constructor() {
    fun toDomain(dbo: TopAnimeDBO): AnimeModel =
        AnimeModel(
            malId = dbo.animeId,
            title = dbo.title,
            imageUrl = dbo.imageUrl,
            score = dbo.rating,
            episodes = dbo.episodes,
        )

    fun fromDomainModel(model: AnimeModel): TopAnimeDBO =
        TopAnimeDBO(
            animeId = model.malId,
            imageUrl = model.imageUrl,
            title = model.title,
            episodes = model.episodes,
            rating = model.score,
        )
}
