package ru.anishark.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.data.remote.datasource.RemoteAnimeDataSource
import ru.anishark.domain.model.AnimeGenreModel
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.model.AnimeRatingModel
import ru.anishark.domain.model.AnimeTypeModel
import ru.anishark.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeDataSource: RemoteAnimeDataSource,
) : AnimeRepository {
    override fun getAllAnimes(
        ratings: List<AnimeRatingModel>,
        genres: List<AnimeGenreModel>,
        type: List<AnimeTypeModel>,
    ): Single<List<AnimeModel>> = animeDataSource.getAnimeSearch(ratings, genres, type)

    override fun getAnime(malId: Int): Single<AnimeModel> = animeDataSource.getAnimeSearch(malId)

    override fun getAnimeByTitle(letter: String): Single<List<AnimeModel>> = animeDataSource.getAnimeByTitle(letter)
}
