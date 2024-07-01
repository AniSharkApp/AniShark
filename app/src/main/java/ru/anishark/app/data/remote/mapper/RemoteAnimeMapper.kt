package ru.anishark.app.data.remote.mapper

import ru.anishark.app.data.remote.dto.response.AnimeDTO
import ru.anishark.app.data.remote.dto.response.AnimeFullDTO
import ru.anishark.app.domain.model.AnimeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteAnimeMapper @Inject constructor() {
    fun toDomainModel(dto: AnimeDTO): AnimeModel =
        with(dto) {
            AnimeModel(
                malId = malId,
                title = titles.first().title,
                titleEnglish = titleEnglish,
                synopsis = synopsis,
                year = year,
                episodes = episodes,
                imageUrl = images.jpeg.imageUrl,
                score = score,
                season = season,
                studio = if (studios.isNotEmpty()) studios.first().name else null,
            )
        }
    fun toDomainModel(dto: AnimeFullDTO): AnimeModel = toDomainModel(dto.data)
}
