package ru.anishark.app.data.db.mapper

import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel

fun BookmarkAnimeEntity.toModel() = BookmarkModel(malId = this.animeId, imageUrl = this.image, title = this.title)

fun BookmarkModel.toEntity() = BookmarkAnimeEntity(animeId = this.malId, image = this.imageUrl, title = this.title)

// TODO: человеческий маппер
fun BookmarkModel.toAnimeModel() = AnimeModel(malId = this.malId, title = this.title, synopsis = "", year = 0, episodes = 0, imageUrl = this.imageUrl, score = 0.0)
