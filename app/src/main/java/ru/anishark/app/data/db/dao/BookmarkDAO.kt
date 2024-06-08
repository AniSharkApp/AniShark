package ru.anishark.app.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anishark.app.data.db.items.BookmarkAnimeEntity

@Dao
interface BookmarkDAO {
    @Query("SELECT * FROM bookmarks")
    fun getAll() : List<BookmarkAnimeEntity>

    @Query("SELECT * FROM bookmarks WHERE animeId = :animeId")
    fun getBookmark(animeId: Int): BookmarkAnimeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(anime: BookmarkAnimeEntity)

    @Delete
    fun deleteBookmark(anime: BookmarkAnimeEntity)
}