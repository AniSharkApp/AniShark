package ru.anishark.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.domain.model.AnimeGenreModel
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.model.AnimeRatingModel
import ru.anishark.domain.model.AnimeTypeModel

interface AnimeRepository {
    fun getAllAnimes(
        ratings: List<AnimeRatingModel> = listOf(),
        genres: List<AnimeGenreModel> = listOf(),
        type: List<AnimeTypeModel> = listOf(),
    ): Single<List<AnimeModel>>

    fun getAnime(malId: Int): Single<AnimeModel>

    fun getAnimeByTitle(letter: String): Single<List<AnimeModel>>
}
