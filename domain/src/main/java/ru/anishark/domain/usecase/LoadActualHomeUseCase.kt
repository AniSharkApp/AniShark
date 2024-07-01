package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Observable
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.repository.SeasonsRepository

class LoadActualHomeUseCase(
    private val seasonsRepository: SeasonsRepository,
) {
    operator fun invoke(): Observable<List<AnimeModel>> = seasonsRepository.getCurrentSeasonAnime()
}
