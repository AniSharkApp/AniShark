package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Completable
import ru.anishark.domain.model.BookmarkModel
import ru.anishark.domain.repository.BookmarkRepository

class AddBookmarkAnimeUseCase(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(model: BookmarkModel): Completable = bookmarkRepository.insertBookmark(model)
}
