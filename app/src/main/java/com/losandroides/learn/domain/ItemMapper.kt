package com.losandroides.learn.domain

import com.losandroides.learn.data.network.item.model.ItemDTO
import com.losandroides.learn.data.network.item.model.ItemsDTO
import com.losandroides.learn.domain.model.Item

fun ItemsDTO.toDomain(): List<Item> {
    return items.map { item ->
        item.toDomain()
    }
}

private fun ItemDTO.toDomain() = Item(title)
