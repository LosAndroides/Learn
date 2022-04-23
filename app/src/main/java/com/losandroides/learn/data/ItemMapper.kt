package com.losandroides.learn.data

import com.losandroides.learn.data.local.database.ItemDatabaseModel
import com.losandroides.learn.data.network.item.model.ItemDTO
import com.losandroides.learn.data.network.item.model.ItemsDTO
import com.losandroides.learn.domain.model.Item

fun ItemsDTO.toDomain(): List<Item> {
    return items.map { item ->
        item.toDomain()
    }
}

private fun ItemDTO.toDomain() = Item(title)

fun List<ItemDatabaseModel>.toDomain(): List<Item> = map { it.toDomain() }
fun ItemDatabaseModel.toDomain() = Item(title)

fun Item.toDatabaseModel() = ItemDatabaseModel(title)
