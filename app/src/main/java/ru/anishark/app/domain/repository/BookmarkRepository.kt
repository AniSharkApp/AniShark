package ru.anishark.app.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel

interface BookmarkRepository {
    fun getAllBookmarks(): Observable<List<BookmarkModel>>

    fun getBookmark(animeId: Int): Observable<BookmarkModel>

    fun insertBookmarkFromAnime(model: AnimeModel): Completable

    fun insertBookmark(model: BookmarkModel): Completable

    fun deleteBookmark(animeId: Int): Completable
}
