package com.losandroides.learn.data.local.datasource

import arrow.core.Either
import com.losandroides.learn.domain.model.Item

interface LocalItemDatasource {
    suspend fun getAllItems(): Either<Throwable, List<Item>>
    suspend fun saveItems(items: List<Item>)
    suspend fun isEmpty(): Boolean
}
