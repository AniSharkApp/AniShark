package ru.anishark.app.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitleDTO(
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String
)
