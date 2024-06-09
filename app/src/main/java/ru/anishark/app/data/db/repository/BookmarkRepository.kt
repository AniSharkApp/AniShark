package ru.anishark.app.data.db.repository

import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val dao: BookmarkDAO
) {
    fun getAll() = dao.getAll()

    fun getBookmark(animeId: Int) = dao.getBookmark(animeId)

    fun insertBookmark(anime: BookmarkAnimeEntity) = dao.insertBookmark(anime)

    fun deleteBookmark(anime: BookmarkAnimeEntity) = dao.deleteBookmark(anime)

}