package com.losandroides.learn.data.local.datasource

import arrow.core.Either
import com.losandroides.learn.data.local.database.ItemDatabase
import com.losandroides.learn.data.toDatabaseModel
import com.losandroides.learn.data.toDomain
import com.losandroides.learn.di.IO
import com.losandroides.learn.domain.model.Item
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultLocalItemDatasource @Inject constructor(
    database: ItemDatabase,
    @IO private val dispatcher: CoroutineDispatcher,
) : LocalItemDatasource {

    private val dao = database.itemDatabaseModelDao()

    override suspend fun isEmpty(): Boolean =
        withContext(dispatcher) { dao.itemsCount() <= 0 }

    override suspend fun saveItems(items: List<Item>) {
        withContext(dispatcher) {
            dao.insertItems(items.map {
                it.toDatabaseModel()
            })
        }
    }

    override suspend fun getAllItems(): Either<Throwable, List<Item>> =
        withContext(dispatcher) {
            Either.catch {
                dao.getAllItems().toDomain()
            }
        }
}
