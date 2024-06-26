package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.domain.repository.BookmarkRepository

class GetOneBookmarkCatalogUseCase(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(animeId: Int): Observable<BookmarkModel> = bookmarkRepository.getBookmark(animeId)
}
