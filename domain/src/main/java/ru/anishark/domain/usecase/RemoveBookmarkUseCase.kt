package ru.anishark.domain.usecase

import ru.anishark.domain.repository.BookmarkRepository

class RemoveBookmarkUseCase(
    private val bookmarksRepository: BookmarkRepository,
) {
    operator fun invoke(animeId: Int) = bookmarksRepository.deleteBookmark(animeId)
}
