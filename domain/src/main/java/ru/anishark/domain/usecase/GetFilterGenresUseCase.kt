package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.anishark.domain.model.AnimeGenreModel
import ru.anishark.domain.repository.GenresRepository

class GetFilterGenresUseCase(
    private val genresRepository: GenresRepository,
) {
    operator fun invoke(): Single<List<AnimeGenreModel>> = genresRepository.getAllGenres()
}
