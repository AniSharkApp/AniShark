package ru.anishark.app.data.db.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkDBO(
    @PrimaryKey
    val animeId: Int,
    val image: String?,
    val title: String,
)
