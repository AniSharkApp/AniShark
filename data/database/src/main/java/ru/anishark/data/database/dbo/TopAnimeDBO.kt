package ru.anishark.data.database.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topAnime")
data class TopAnimeDBO(
    @PrimaryKey
    val animeId: Int,
    val imageUrl: String?,
    val title: String,
    val episodes: Int?,
    val rating: Double?,
)
