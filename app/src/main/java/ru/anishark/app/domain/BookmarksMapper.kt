package ru.anishark.app.domain

import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.domain.model.BookmarkModel

fun BookmarkAnimeEntity.toModel() = BookmarkModel(animeId = this.animeId, image = this.image, title = this.title)

fun BookmarkModel.toEntity() = BookmarkAnimeEntity(animeId = this.animeId, image = this.image, title = this.title)