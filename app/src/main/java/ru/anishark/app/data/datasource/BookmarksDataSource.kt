package ru.anishark.app.data.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.domain.model.BookmarkModel

interface BookmarksDataSource {
    fun getAll(): Flowable<List<BookmarkModel>>

    fun getBookmark(animeId: Int): Flowable<BookmarkModel>

    fun insertBookmark(anime: BookmarkModel): Completable

    fun deleteBookmark(anime: BookmarkModel): Completable
}