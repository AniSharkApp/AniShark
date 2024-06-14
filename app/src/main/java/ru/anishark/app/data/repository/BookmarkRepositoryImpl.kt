package ru.anishark.app.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dataSorce: DatabaseBookmarksDataSource
) : BookmarkRepository {
    override fun getAllBookmarks(): Single<List<BookmarkModel>> = dataSorce.getAllBookmarks()

    override fun getBookmark(animeId: Int): Single<BookmarkModel> = dataSorce.getBookmark(animeId)

    override fun insertBookmark(anime: AnimeModel) = dataSorce.createBookmark(anime)

    override fun deleteBookmark(animeId: Int) = dataSorce.removeBookmark(animeId)

}