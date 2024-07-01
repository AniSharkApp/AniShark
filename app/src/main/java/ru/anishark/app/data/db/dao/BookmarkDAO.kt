package ru.anishark.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.data.db.items.BookmarkAnimeEntity

@Dao
interface BookmarkDAO {
    @Query("SELECT * FROM bookmarks")
    fun getAll(): Observable<List<BookmarkAnimeEntity>>

    @Query("SELECT * FROM bookmarks WHERE animeId = :animeId")
    fun getBookmark(animeId: Int): Observable<BookmarkAnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(anime: BookmarkAnimeEntity): Completable

    @Query("DELETE FROM bookmarks WHERE animeId = :animeId")
    fun deleteBookmark(animeId: Int): Completable

}