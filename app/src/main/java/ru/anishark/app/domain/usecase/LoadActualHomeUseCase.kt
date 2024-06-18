package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.SeasonsRepository

class LoadActualHomeUseCase(
    private val seasonsRepository: SeasonsRepository,
) {
    operator fun invoke(): Single<List<AnimeModel>> = seasonsRepository.getCurrentSeasonAnime()
}
