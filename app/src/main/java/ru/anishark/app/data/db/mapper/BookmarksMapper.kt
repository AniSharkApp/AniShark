package ru.anishark.app.data.db.mapper

import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.domain.model.BookmarkModel

fun BookmarkAnimeEntity.toModel() = BookmarkModel(malId = this.animeId, imageUrl = this.image, title = this.title)

fun BookmarkModel.toEntity() = BookmarkAnimeEntity(animeId = this.malId, image = this.imageUrl, title = this.title)
