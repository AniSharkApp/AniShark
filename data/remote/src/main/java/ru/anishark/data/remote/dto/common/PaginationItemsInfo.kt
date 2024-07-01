package ru.anishark.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationItemsInfo(
    @SerialName("count")
    val count: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("per_page")
    val perPage: Int,
)
