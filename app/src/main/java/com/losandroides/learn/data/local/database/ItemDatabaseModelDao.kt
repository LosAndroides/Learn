package com.losandroides.learn.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDatabaseModelDao {

    @Query("SELECT * FROM ItemDatabaseModel")
    suspend fun getAllItems(): List<ItemDatabaseModel>

    @Query("SELECT COUNT(title) FROM ItemDatabaseModel")
    suspend fun itemsCount(): Int

    @Insert
    suspend fun insertItems(items: List<ItemDatabaseModel>)
}
