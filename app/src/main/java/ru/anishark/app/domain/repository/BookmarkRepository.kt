package ru.anishark.app.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel

interface BookmarkRepository {
    fun getAllBookmarks(): Flowable<List<BookmarkModel>>
    fun getBookmark(animeId: Int): Flowable<BookmarkModel>
    fun insertBookmark(anime: AnimeModel): Completable
    fun deleteBookmark(animeId: Int): Completable
}