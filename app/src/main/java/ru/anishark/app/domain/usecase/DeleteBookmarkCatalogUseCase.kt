package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Completable
import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.data.repository.BookmarkRepositoryImpl
import ru.anishark.app.domain.model.BookmarkModel

class DeleteBookmarkCatalogUseCase(
    private val bookmarksDataSource: DatabaseBookmarksDataSource
) {
    operator fun invoke(anime: BookmarkModel): Completable = bookmarksDataSource.deleteBookmark(anime)
}