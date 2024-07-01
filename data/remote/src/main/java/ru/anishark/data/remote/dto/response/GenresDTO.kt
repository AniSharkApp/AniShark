package ru.anishark.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresDTO(
    @SerialName("data")
    val data: List<GenreDTO>,
)
