package com.losandroides.learn.domain

import com.losandroides.learn.data.network.item.RemoteItemDatasource
import com.losandroides.learn.domain.model.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val remoteItemDatasource: RemoteItemDatasource
) {
    suspend fun getItems(): List<Item> {
        return remoteItemDatasource.getItemsDTO().toDomain()
    }
}
