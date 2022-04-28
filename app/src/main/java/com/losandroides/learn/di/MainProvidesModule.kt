package com.losandroides.learn.di

import android.app.Application
import androidx.room.Room
import com.losandroides.learn.data.local.database.ItemDatabase
import com.losandroides.learn.data.local.datasource.DefaultLocalItemDatasource
import com.losandroides.learn.data.local.datasource.LocalItemDatasource
import com.losandroides.learn.data.network.RetrofitClient
import com.losandroides.learn.data.network.item.ItemService
import com.losandroides.learn.data.network.item.datasource.DefaultRemoteItemDatasource
import com.losandroides.learn.data.network.item.datasource.RemoteItemDatasource
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
    fun provideItemDatabase(app: Application): ItemDatabase = Room.databaseBuilder(
        app,
        ItemDatabase::class.java,
        "item-db"
    ).build()

    @Provides
    fun provideItemService(retrofitClient: RetrofitClient): ItemService = retrofitClient.itemService

    @Provides
    fun provideLocalPointsDataSource(database: ItemDatabase): LocalItemDatasource =
        DefaultLocalItemDatasource(database)

    @Provides
    fun provideRemoteItemDatasource(itemService: ItemService): RemoteItemDatasource =
        DefaultRemoteItemDatasource(itemService)
}
