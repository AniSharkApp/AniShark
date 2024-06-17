package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource
import ru.anishark.app.domain.model.BookmarkModel

class GetOneBookmarkCatalogUseCase(
    private val bookmarksDataSource: DatabaseBookmarksDataSource,
) {
    operator fun invoke(animeId: Int): Observable<BookmarkModel> = bookmarksDataSource.getBookmark(animeId)
}