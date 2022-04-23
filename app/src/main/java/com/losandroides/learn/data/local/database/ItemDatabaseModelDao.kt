package com.losandroides.learn.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDatabaseModelDao {

    @Query("SELECT * FROM ItemDatabaseModel")
    fun getAllItems(): List<ItemDatabaseModel>

    @Query("SELECT COUNT(title) FROM ItemDatabaseModel")
    fun itemsCount(): Int

    @Insert
    fun insertItems(items: List<ItemDatabaseModel>)
}
