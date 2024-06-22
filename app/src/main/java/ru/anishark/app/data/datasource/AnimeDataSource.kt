package ru.anishark.app.data.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.AnimeRatingModel
import ru.anishark.app.domain.model.AnimeTypeModel

interface AnimeDataSource {
    fun getAnimeSearch(
        ratings: List<AnimeRatingModel> = listOf(),
        genres: List<AnimeGenreModel> = listOf(),
        type: List<AnimeTypeModel> = listOf(),
    ): Single<List<AnimeModel>>
}
