package ru.anishark.app.data.db.mapper

import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarksMapper @Inject constructor() {
    fun toDomainModel(input: BookmarkAnimeEntity): BookmarkModel = BookmarkModel(input.animeId, input.image, input.title)

    fun fromDomainModel(input: BookmarkModel): BookmarkAnimeEntity = BookmarkAnimeEntity(input.malId, input.imageUrl, input.title)

    fun fromAnimeDomainModel(input: AnimeModel): BookmarkAnimeEntity = BookmarkAnimeEntity(input.malId, input.imageUrl, input.title)
}
