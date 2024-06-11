package ru.anishark.app.domain.usecase

import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource

class DeleteBookmarkCatalogUseCase(
    private val bookmarksDataSource: DatabaseBookmarksDataSource
) {
    operator fun invoke(animeId: Int) = bookmarksDataSource.removeBookmark(animeId)
}