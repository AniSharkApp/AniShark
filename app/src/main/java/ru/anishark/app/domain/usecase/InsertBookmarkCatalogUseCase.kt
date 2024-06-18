package ru.anishark.app.domain.usecase

import io.reactivex.rxjava3.core.Completable
import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource
import ru.anishark.app.domain.model.AnimeModel

class InsertBookmarkCatalogUseCase(
    private val bookmarksDataSource: DatabaseBookmarksDataSource,
) {
    operator fun invoke(anime: AnimeModel): Completable = bookmarksDataSource.createBookmark(anime)

}