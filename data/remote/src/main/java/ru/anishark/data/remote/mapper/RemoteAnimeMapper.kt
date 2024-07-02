package ru.anishark.data.remote.mapper

import ru.anishark.data.remote.dto.response.AnimeDTO
import ru.anishark.data.remote.dto.response.AnimeFullDTO
import ru.anishark.domain.model.AnimeModel

class RemoteAnimeMapper {
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
