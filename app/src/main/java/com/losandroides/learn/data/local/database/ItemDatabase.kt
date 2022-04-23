package com.losandroides.learn.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ItemDatabaseModel::class],
    version = 1
)

abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDatabaseModelDao(): ItemDatabaseModelDao
}
