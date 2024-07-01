package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.repository.AnimeRepository

class GetOnClickAnimeUseCase(
    private val animeRepository: AnimeRepository,
) {
    operator fun invoke(malId: Int): Single<AnimeModel> = animeRepository.getAnime(malId)
}
