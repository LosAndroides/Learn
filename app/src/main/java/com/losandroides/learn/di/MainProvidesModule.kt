package com.losandroides.learn.di

import android.content.Context
import com.losandroides.learn.data.network.RetrofitClient
import com.losandroides.learn.data.network.item.ItemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainProvidesModule {

    @Provides
    fun provideRetrofitClient(@ApplicationContext appContext: Context): RetrofitClient = RetrofitClient(appContext)

    @Provides
    fun provideItemService(retrofitClient: RetrofitClient): ItemService = retrofitClient.itemService
}
