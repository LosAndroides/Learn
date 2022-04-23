package com.losandroides.learn.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemDatabaseModel(
    @PrimaryKey
    val title: String
)
