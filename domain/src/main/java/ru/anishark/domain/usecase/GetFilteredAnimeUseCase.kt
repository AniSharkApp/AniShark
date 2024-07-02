package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.anishark.domain.model.AnimeGenreModel
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.model.AnimeRatingModel
import ru.anishark.domain.model.AnimeTypeModel
import ru.anishark.domain.repository.AnimeRepository

class GetFilteredAnimeUseCase(
    private val animeRepository: AnimeRepository,
) {
    operator fun invoke(
        ratings: List<AnimeRatingModel> = listOf(),
        genres: List<AnimeGenreModel> = listOf(),
        type: List<AnimeTypeModel> = listOf(),
    ): Single<List<AnimeModel>> = animeRepository.getAllAnimes(ratings, genres, type)
}
