package ru.anishark.app.data.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel

interface BookmarksDataSource {
    fun getAllBookmarks(): Flowable<List<BookmarkModel>>

    fun getBookmark(animeId: Int): Flowable<BookmarkModel>


    fun createBookmark(anime: AnimeModel): Completable

    fun removeBookmark(animeId: Int): Completable
}