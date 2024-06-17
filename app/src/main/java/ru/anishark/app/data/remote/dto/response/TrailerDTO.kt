package ru.anishark.app.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerDTO(
    @SerialName("youtube_id")
    val youtubeId: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("embed_url")
    val embedUrl: String?,
)
