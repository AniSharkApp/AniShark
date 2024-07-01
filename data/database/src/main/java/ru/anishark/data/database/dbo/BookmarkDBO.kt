package ru.anishark.data.database.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkDBO(
    @PrimaryKey
    val animeId: Int,
    val image: String?,
    val title: String,
)
