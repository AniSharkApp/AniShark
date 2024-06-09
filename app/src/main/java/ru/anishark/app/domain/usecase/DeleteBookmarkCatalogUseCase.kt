package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Completable
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.data.db.repository.BookmarkRepository

class DeleteBookmarkCatalogUseCase(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(anime: BookmarkAnimeEntity): Completable = bookmarkRepository.deleteBookmark(anime)
}