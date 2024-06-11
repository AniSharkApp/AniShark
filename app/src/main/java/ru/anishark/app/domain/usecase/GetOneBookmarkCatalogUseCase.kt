package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Flowable
import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource
import ru.anishark.app.domain.model.BookmarkModel

class GetOneBookmarkCatalogUseCase (
    private val bookmarksDataSource: DatabaseBookmarksDataSource
){
    operator fun invoke(animeId: Int): Flowable<BookmarkModel> = bookmarksDataSource.getBookmark(animeId)
}