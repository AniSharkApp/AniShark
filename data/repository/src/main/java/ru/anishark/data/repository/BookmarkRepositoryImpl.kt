package ru.anishark.data.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.data.database.datasource.DatabaseBookmarksDataSource
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.model.BookmarkModel
import ru.anishark.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dataSorce: DatabaseBookmarksDataSource,
) : BookmarkRepository {
    override fun getAllBookmarks(): Observable<List<BookmarkModel>> = dataSorce.getAllBookmarks()

    override fun getBookmark(animeId: Int): Observable<BookmarkModel> = dataSorce.getBookmark(animeId)

    override fun insertBookmarkFromAnime(model: AnimeModel) = dataSorce.createBookmarkFromAnime(model)

    override fun insertBookmark(model: BookmarkModel): Completable = dataSorce.createBookmark(model)

    override fun deleteBookmark(animeId: Int) = dataSorce.removeBookmark(animeId)
}
