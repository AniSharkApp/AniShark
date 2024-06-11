package ru.anishark.app.domain.usecase

import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource
import ru.anishark.app.domain.model.AnimeModel

class InsertBookmarkCatalogUseCase(
    private val bookmarksDataSource: DatabaseBookmarksDataSource
) {
    operator fun invoke(anime: AnimeModel) = bookmarksDataSource.createBookmark(anime)
}