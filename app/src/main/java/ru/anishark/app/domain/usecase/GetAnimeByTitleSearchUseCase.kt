package ru.anishark.app.domain.usecase

import ru.anishark.app.domain.repository.AnimeRepository

class GetAnimeByTitleSearchUseCase(
    private val animeRepository: AnimeRepository,
) {
    operator fun invoke(
        letter: String
    ) = animeRepository.getAnimeByTitle(letter)
}
