package com.losandroides.learn.data.network.item.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDTO(
    @Json(name = "title")
    val title: String
)
