package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.TopRepository

class LoadTopsHomeUseCase(
    private val topRepository: TopRepository,
) {
    operator fun invoke(): Single<List<AnimeModel>> = topRepository.getTopAnime()
}
