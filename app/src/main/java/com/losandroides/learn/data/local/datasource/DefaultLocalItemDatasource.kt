package com.losandroides.learn.data.local.datasource

import arrow.core.Either
import com.losandroides.learn.data.local.database.ItemDatabase
import com.losandroides.learn.data.toDatabaseModel
import com.losandroides.learn.data.toDomain
import com.losandroides.learn.domain.model.Item
import javax.inject.Inject

class DefaultLocalItemDatasource @Inject constructor(database: ItemDatabase) : LocalItemDatasource {

    private val dao = database.itemDatabaseModelDao()

    override suspend fun isEmpty(): Boolean =
        dao.itemsCount() <= 0

    override suspend fun saveItems(items: List<Item>) {
        dao.insertItems(items.map {
            it.toDatabaseModel()
        })
    }

    override suspend fun getAllItems(): Either<Throwable, List<Item>> =
        Either.catch {
            dao.getAllItems().toDomain()
        }
}
