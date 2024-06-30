package ru.anishark.app.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.api.AnimeService
import ru.anishark.app.data.remote.dto.response.AnimeDTO
import ru.anishark.app.data.remote.dto.response.SearchAnimeDTO
import ru.anishark.app.data.remote.mapper.RemoteAnimeMapper
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.AnimeRatingModel
import ru.anishark.app.domain.model.AnimeTypeModel
import javax.inject.Inject

class RemoteAnimeDataSource @Inject constructor(
    private val animeService: AnimeService,
    private val remoteAnimeMapper: RemoteAnimeMapper,
) {
    fun getAnimeSearch(
        ratings: List<AnimeRatingModel>,
        genres: List<AnimeGenreModel>,
        type: List<AnimeTypeModel>,
    ): Single<List<AnimeModel>> =
        animeService
            .getAnimeListUsingParameters(
                mapOf(
                    "rating" to ratings.map { it.backingName }.joinToString(separator = ","),
                    "genres" to genres.map { it.malId }.joinToString(separator = ","),
                    "type" to type.map { it.backingName }.joinToString(separator = ","),
                ),
            ).map { v: SearchAnimeDTO ->
                v.data.map(remoteAnimeMapper::toDomainModel)
            }

    fun getAnimeSearch(malId: Int): Single<AnimeModel> =
        animeService
            .getAnime(malId)
            .map(remoteAnimeMapper::toDomainModel)

    fun getAnimeByTitle(letter: String): Single<List<AnimeModel>> =
        animeService
            .getAnimeByTitle(letter)
            .map { v: SearchAnimeDTO ->
                v.data.map(remoteAnimeMapper::toDomainModel)
            }
}
