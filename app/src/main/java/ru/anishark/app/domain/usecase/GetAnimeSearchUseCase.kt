package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.AnimeRatingModel
import ru.anishark.app.domain.model.AnimeTypeModel
import ru.anishark.app.domain.repository.AnimeRepository

class GetAnimeSearchUseCase(
    private val animeRepository: AnimeRepository,
) {
    operator fun invoke(
        ratings: List<AnimeRatingModel> = listOf(),
        genres: List<AnimeGenreModel> = listOf(),
        type: List<AnimeTypeModel> = listOf(),
    ): Single<List<AnimeModel>> = animeRepository.getAllAnimes(ratings, genres, type)
}
