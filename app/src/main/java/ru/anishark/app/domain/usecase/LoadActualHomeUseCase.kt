package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.SeasonsRepository

class LoadActualHomeUseCase(
    private val seasonsRepository: SeasonsRepository,
) {
    operator fun invoke(): Observable<List<AnimeModel>> = seasonsRepository.getCurrentSeasonAnime()
}
