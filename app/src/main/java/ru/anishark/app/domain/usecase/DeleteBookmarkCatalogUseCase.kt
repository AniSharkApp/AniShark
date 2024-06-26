package ru.anishark.app.domain.usecase

import ru.anishark.app.domain.repository.BookmarkRepository

class DeleteBookmarkCatalogUseCase(
    private val bookmarksRepository: BookmarkRepository,
) {
    operator fun invoke(animeId: Int) = bookmarksRepository.deleteBookmark(animeId)
}
