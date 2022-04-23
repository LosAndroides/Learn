package com.losandroides.learn.domain

import arrow.core.Either
import arrow.core.left
import com.losandroides.learn.data.local.datasource.LocalItemDatasource
import com.losandroides.learn.data.network.item.datasource.RemoteItemDatasource
import com.losandroides.learn.data.toDomain
import com.losandroides.learn.domain.model.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val localItemDatasource: LocalItemDatasource,
    private val remoteItemDatasource: RemoteItemDatasource
) {
    suspend fun getItems(): Either<Throwable, List<Item>> {
        if (localItemDatasource.isEmpty()) {
            remoteItemDatasource.getItemsDTO()
                .fold(
                    {
                        return it.left()
                    },
                    {
                        localItemDatasource.saveItems(it.toDomain())
                    })
        }
        return localItemDatasource.getAllItems()
    }
}
