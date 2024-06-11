package ru.anishark.app.data.db.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import ru.anishark.app.data.datasource.BookmarksDataSource
import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.domain.toModel
import javax.inject.Inject

class DatabaseBookmarksDataSource @Inject constructor(
    private val dao: BookmarkDAO
) : BookmarksDataSource {
    override fun getAll(): Flowable<List<BookmarkModel>> = dao.getAll().map { it.map { entity -> entity.toModel()} }

    override fun getBookmark(animeId: Int): Flowable<BookmarkModel> = dao.getBookmark(animeId).map { it.toModel() }

    override fun insertBookmark(anime: BookmarkModel): Completable =  dao.insertBookmark(anime)

    override fun deleteBookmark(anime: BookmarkModel): Completable = dao.deleteBookmark(anime)

}