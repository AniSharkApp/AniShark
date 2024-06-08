package ru.anishark.app.data.db.items

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkAnimeEntity(
    @PrimaryKey
    val animeId: Int,
    val image: String, // TODO: переделать для картинок
    val title: String
)
