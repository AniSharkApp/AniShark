package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Flowable
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.data.db.repository.BookmarkRepository

class GetAllBookmarksCatalogUseCase (
    private val bookmarksRepository: BookmarkRepository
) {
    operator fun invoke(): Flowable<List<BookmarkAnimeEntity>> = bookmarksRepository.getAll()
}