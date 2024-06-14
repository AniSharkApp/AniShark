package ru.anishark.app.data.db.datasource


import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.datasource.BookmarksDataSource
import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.data.db.mapper.toModel
import javax.inject.Inject

class DatabaseBookmarksDataSource @Inject constructor(
    private val dao: BookmarkDAO
) : BookmarksDataSource {
    override fun getAllBookmarks(): Single<List<BookmarkModel>> =
        dao.getAll().map { it.map { entity -> entity.toModel() } }

    override fun getBookmark(animeId: Int): Single<BookmarkModel> =
        dao.getBookmark(animeId).map { it.toModel() }

    override fun createBookmark(anime: AnimeModel): Completable {
        val bookmark = BookmarkAnimeEntity(anime.malId, anime.imageUrl, anime.title)
        return dao.insertBookmark(bookmark)
    }

    override fun removeBookmark(animeId: Int) = dao.deleteBookmark(animeId)

}