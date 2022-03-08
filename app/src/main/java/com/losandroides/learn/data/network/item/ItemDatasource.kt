package com.losandroides.learn.data.network.item

import com.losandroides.learn.data.network.item.model.ItemsDTO

interface ItemDatasource {
    suspend fun getItemsDTO(): ItemsDTO
}
