package ru.anishark.app.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anishark.app.data.db.items.BookmarkAnime

@Dao
interface BookmarkDAO {
    @Query("SELECT * FROM bookmarks")
    fun getAll() : List<BookmarkAnime>

    @Query("SELECT * FROM bookmarks WHERE animeId = :animeId")
    fun getBookmark(animeId: Int): BookmarkAnime

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(anime: BookmarkAnime)

    @Delete
    fun deleteBookmark(anime: BookmarkAnime)
}