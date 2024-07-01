package ru.anishark.app.data.db.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actualAnime")
data class ActualAnimeDBO(
    @PrimaryKey
    val animeId: Int,
    val imageUrl: String?,
    val title: String,
    val episodes: Int?,
    val rating: Double?,
)
