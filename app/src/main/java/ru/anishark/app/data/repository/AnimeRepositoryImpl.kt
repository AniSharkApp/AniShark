package ru.anishark.app.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.datasource.RemoteAnimeDataSource
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.AnimeRatingModel
import ru.anishark.app.domain.model.AnimeTypeModel
import ru.anishark.app.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeDataSource: RemoteAnimeDataSource,
) : AnimeRepository {
    override fun getAllAnimes(
        ratings: List<AnimeRatingModel>,
        genres: List<AnimeGenreModel>,
        type: List<AnimeTypeModel>,
    ): Single<List<AnimeModel>> = animeDataSource.getAnimeSearch(ratings, genres, type)
}
