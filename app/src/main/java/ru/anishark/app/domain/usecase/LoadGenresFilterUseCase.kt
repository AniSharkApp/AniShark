package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.repository.GenresRepository

class LoadGenresFilterUseCase(
    private val genresRepository: GenresRepository,
) {
    operator fun invoke(): Single<List<AnimeGenreModel>> = genresRepository.getAllGenres()
}
