package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Completable
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.domain.repository.BookmarkRepository

class InsertBookmarkAnimeUseCase(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(model: BookmarkModel): Completable = bookmarkRepository.insertBookmark(model)
}
