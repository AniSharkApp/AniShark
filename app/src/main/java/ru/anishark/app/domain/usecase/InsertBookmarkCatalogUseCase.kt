package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Completable
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.BookmarkRepository

class InsertBookmarkCatalogUseCase(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(anime: AnimeModel): Completable = bookmarkRepository.insertBookmark(anime)
}
