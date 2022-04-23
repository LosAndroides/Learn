package com.losandroides.learn.data.network.item.datasource

import arrow.core.Either
import com.losandroides.learn.data.network.item.model.ItemsDTO

interface RemoteItemDatasource {
    suspend fun getItemsDTO(): Either<Throwable, ItemsDTO>
}
