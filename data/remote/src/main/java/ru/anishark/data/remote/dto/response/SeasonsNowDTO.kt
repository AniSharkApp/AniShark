package ru.anishark.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.anishark.data.remote.dto.common.PaginationData

@Serializable
data class SeasonsNowDTO(
    @SerialName("pagination")
    val pagination: PaginationData,
    @SerialName("data")
    val data: List<AnimeDTO>,
)
