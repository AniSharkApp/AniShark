package ru.anishark.app.data.repository

import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.domain.repository.BookmarkRepository
import ru.anishark.app.domain.toEntity
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: BookmarkDAO
) : BookmarkRepository {
    fun getAll() = dao.getAll()

    fun getBookmark(animeId: Int) = dao.getBookmark(animeId)

    fun insertBookmark(anime: BookmarkModel) = dao.insertBookmark(anime.toEntity())

    fun deleteBookmark(anime: BookmarkModel) = dao.deleteBookmark(anime.toEntity())

}