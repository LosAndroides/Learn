package com.losandroides.learn.data.network.item

import com.losandroides.learn.data.network.item.model.ItemsDTO
import javax.inject.Inject

class RemoteItemDatasource @Inject constructor(
    private val itemService: ItemService
) : ItemDatasource {
    override suspend fun getItemsDTO(): ItemsDTO = itemService.getItemsDTO()
}
