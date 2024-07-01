package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Observable
import ru.anishark.domain.model.BookmarkModel
import ru.anishark.domain.repository.BookmarkRepository

class GetOneBookmarkCatalogUseCase(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(animeId: Int): Observable<BookmarkModel> = bookmarkRepository.getBookmark(animeId)
}
