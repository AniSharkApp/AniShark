package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource
import ru.anishark.app.domain.model.BookmarkModel

class GetAllBookmarksCatalogUseCase(
    private val bookmarksDataSource: DatabaseBookmarksDataSource,
) {
    operator fun invoke(): Single<List<BookmarkModel>> = bookmarksDataSource.getAllBookmarks()
}
