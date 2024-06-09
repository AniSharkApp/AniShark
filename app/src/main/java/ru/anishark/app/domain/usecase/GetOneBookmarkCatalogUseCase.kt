package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Flowable
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.data.db.repository.BookmarkRepository

class GetOneBookmarkCatalogUseCase (
    private val bookmarkRepository: BookmarkRepository
){
    operator fun invoke(animeId: Int): Flowable<BookmarkAnimeEntity> = bookmarkRepository.getBookmark(animeId)
}