package ru.anishark.domain.usecase

import io.reactivex.rxjava3.core.Completable
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.repository.BookmarkRepository

class InsertBookmarkCatalogUseCase(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(anime: AnimeModel): Completable = bookmarkRepository.insertBookmarkFromAnime(anime)
}
