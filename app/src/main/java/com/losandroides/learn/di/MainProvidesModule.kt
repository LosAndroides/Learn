package com.losandroides.learn.di

import com.losandroides.learn.data.network.RetrofitClient
import com.losandroides.learn.data.network.item.ItemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainProvidesModule {

    @Provides
    fun provideRetrofitClient(): RetrofitClient = RetrofitClient()

    @Provides
    fun provideItemService(retrofitClient: RetrofitClient): ItemService = retrofitClient.itemService
}
