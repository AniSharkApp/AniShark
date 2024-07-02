package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Observable
import ru.anishark.domain.model.BookmarkModel
import ru.anishark.domain.repository.BookmarkRepository

class GetAllBookmarksUseCase(
    private val bookmarksRepository: BookmarkRepository,
) {
    operator fun invoke(): Observable<List<BookmarkModel>> = bookmarksRepository.getAllBookmarks()
}
