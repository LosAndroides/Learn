package com.losandroides.learn.data.network.item.datasource

import arrow.core.Either
import com.losandroides.learn.data.network.item.ItemService
import com.losandroides.learn.data.network.item.model.ItemsDTO
import javax.inject.Inject

class DefaultRemoteItemDatasource @Inject constructor(
    private val itemService: ItemService
) : RemoteItemDatasource {
    override suspend fun getItemsDTO(): Either<Throwable, ItemsDTO> =
        Either.catch { itemService.getItemsDTO() }
}
