package com.losandroides.learn.data.network.item

import com.losandroides.learn.data.network.item.model.ItemsDTO
import retrofit2.http.GET

interface ItemService {

    @GET("items.json")
    suspend fun getItemsDTO(): ItemsDTO
}
