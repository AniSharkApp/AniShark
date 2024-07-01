package ru.anishark.data.database.mapper

import ru.anishark.data.database.dbo.ActualAnimeDBO
import ru.anishark.domain.model.AnimeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActualMapper @Inject constructor() {
    fun toDomain(dbo: ActualAnimeDBO): AnimeModel =
        AnimeModel(
            malId = dbo.animeId,
            title = dbo.title,
            imageUrl = dbo.imageUrl,
            score = dbo.rating,
            episodes = dbo.episodes,
        )

    fun fromDomainModel(model: AnimeModel): ActualAnimeDBO =
        ActualAnimeDBO(
            animeId = model.malId,
            imageUrl = model.imageUrl,
            title = model.title,
            episodes = model.episodes,
            rating = model.score,
        )
}
