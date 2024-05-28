package ru.anishark.app.data.db.repository

import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.data.db.items.BookmarkAnime
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val dao: BookmarkDAO
) {
    suspend fun getAll() = dao.getAll()

    suspend fun getBookmark(animeId: Int) = dao.getBookmark(animeId)

    suspend fun insertBookmark(anime: BookmarkAnime) = dao.insertBookmark(anime)

    suspend fun deleteBookmark(anime: BookmarkAnime) = dao.deleteBookmark(anime)

}