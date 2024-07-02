package ru.anishark.domain.usecase

import ru.anishark.domain.repository.AnimeRepository

class GetAnimeSearchUseCase(
    private val animeRepository: AnimeRepository,
) {
    operator fun invoke(letter: String) = animeRepository.getAnimeByTitle(letter)
}
