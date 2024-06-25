package ru.anishark.app.data.db.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.data.db.mapper.BookmarksMapper
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel
import javax.inject.Inject

class DatabaseBookmarksDataSource @Inject constructor(
    private val dao: BookmarkDAO,
    private val bookmarksMapper: BookmarksMapper,
) {
    fun getAllBookmarks(): Observable<List<BookmarkModel>> = dao.getAll().map { it.map(bookmarksMapper::toDomainModel) }

    fun getBookmark(animeId: Int): Observable<BookmarkModel> = dao.getBookmark(animeId).map(bookmarksMapper::toDomainModel)

    fun createBookmark(model: BookmarkModel): Completable = dao.insertBookmark(bookmarksMapper.fromDomainModel(model))

    fun createBookmarkFromAnime(anime: AnimeModel): Completable = dao.insertBookmark(bookmarksMapper.fromAnimeDomainModel(anime))

    fun removeBookmark(animeId: Int) = dao.deleteBookmark(animeId)
}
