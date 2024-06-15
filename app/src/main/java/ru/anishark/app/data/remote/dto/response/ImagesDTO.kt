package ru.anishark.app.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesDTO(
    @SerialName("jpg")
    val jpeg: ImageDTO,
    @SerialName("webp")
    val webp: ImageDTO,
)
