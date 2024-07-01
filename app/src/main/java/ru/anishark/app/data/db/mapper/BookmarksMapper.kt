package ru.anishark.app.data.db.mapper

import ru.anishark.app.data.db.dbo.BookmarkDBO
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarksMapper @Inject constructor() {
    fun toDomainModel(input: BookmarkDBO): BookmarkModel = BookmarkModel(input.animeId, input.image, input.title)

    fun fromDomainModel(input: BookmarkModel): BookmarkDBO = BookmarkDBO(input.malId, input.imageUrl, input.title)

    fun fromAnimeDomainModel(input: AnimeModel): BookmarkDBO = BookmarkDBO(input.malId, input.imageUrl, input.title)
}
