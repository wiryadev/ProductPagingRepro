package com.wiryadev.productpagingrepro.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ProductEntity::class,
        RemoteKeys::class,
    ],
    version = 1, exportSchema = false
)
abstract class SecondhandDatabase : RoomDatabase() {
    abstract fun productCacheDao(): ProductCacheDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}