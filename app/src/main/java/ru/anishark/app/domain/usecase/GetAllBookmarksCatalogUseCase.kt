package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.domain.repository.BookmarkRepository

class GetAllBookmarksCatalogUseCase(
    private val bookmarksRepository: BookmarkRepository,
) {
    operator fun invoke(): Observable<List<BookmarkModel>> = bookmarksRepository.getAllBookmarks()
}
