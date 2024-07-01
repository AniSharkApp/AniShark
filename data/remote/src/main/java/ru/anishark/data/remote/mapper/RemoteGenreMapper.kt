package ru.anishark.data.remote.mapper

import ru.anishark.data.remote.dto.response.GenreDTO
import ru.anishark.domain.model.AnimeGenreModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteGenreMapper @Inject constructor() {
    fun toDomainModel(dto: GenreDTO): AnimeGenreModel =
        with(dto) {
            AnimeGenreModel(
                malId = malId,
                name = name,
                url = url,
                count = count,
            )
        }
}
