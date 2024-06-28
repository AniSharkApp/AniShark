package ru.anishark.app.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudioDTO(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
