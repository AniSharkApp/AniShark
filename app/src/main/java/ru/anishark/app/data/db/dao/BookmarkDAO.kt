package ru.anishark.app.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.data.db.dbo.BookmarkDBO

@Dao
interface BookmarkDAO {
    @Query("SELECT * FROM bookmarks")
    fun getAll(): Observable<List<BookmarkDBO>>

    @Query("SELECT * FROM bookmarks WHERE animeId = :animeId")
    fun getBookmark(animeId: Int): Observable<BookmarkDBO>

    @Upsert
    fun insertBookmark(anime: BookmarkDBO): Completable

    @Query("DELETE FROM bookmarks WHERE animeId = :animeId")
    fun deleteBookmark(animeId: Int): Completable
}
