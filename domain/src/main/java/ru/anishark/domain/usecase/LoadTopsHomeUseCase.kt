package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Observable
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.repository.TopRepository

class LoadTopsHomeUseCase(
    private val topRepository: TopRepository,
) {
    operator fun invoke(): Observable<List<AnimeModel>> = topRepository.getTopAnime()
}
