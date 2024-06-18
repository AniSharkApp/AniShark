package ru.anishark.app.data.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel

interface BookmarksDataSource {
    fun getAllBookmarks(): Observable<List<BookmarkModel>>

    fun getBookmark(animeId: Int): Observable<BookmarkModel>

    fun createBookmark(anime: AnimeModel): Completable

    fun removeBookmark(animeId: Int): Completable
}

