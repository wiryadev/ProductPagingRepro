package com.wiryadev.productpagingrepro.data.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import javax.inject.Inject

interface ProductLocalDataSource {

    suspend fun <R> cacheProductTransaction(block: suspend () -> R): R
    fun getCachedProducts(): PagingSource<Int, ProductEntity>
    suspend fun cacheAllProducts(products: List<ProductEntity>)
    suspend fun clearCachedProducts()
    suspend fun insertRemoteKeys(remoteKeys: List<RemoteKeys>)
    suspend fun getRemoteKeysId(productId: Int): RemoteKeys?
    suspend fun clearRemoteKeys()

}

class ProductLocalDataSourceImpl @Inject constructor(
    private val database: SecondhandDatabase,
    private val productCacheDao: ProductCacheDao,
    private val remoteKeysDao: RemoteKeysDao,
) : ProductLocalDataSource {

    override suspend fun <R> cacheProductTransaction(block: suspend () -> R): R {
        return database.withTransaction(block)
    }

    override fun getCachedProducts(): PagingSource<Int, ProductEntity> {
        return productCacheDao.getCachedProducts()
    }

    override suspend fun cacheAllProducts(products: List<ProductEntity>) {
        productCacheDao.insertOrReplace(products)
    }

    override suspend fun clearCachedProducts() {
        productCacheDao.deleteAll()
    }

    override suspend fun insertRemoteKeys(remoteKeys: List<RemoteKeys>) {
        remoteKeysDao.insertAll(remoteKeys)
    }

    override suspend fun getRemoteKeysId(productId: Int): RemoteKeys? {
        return remoteKeysDao.getRemoteKeysId(productId)
    }

    override suspend fun clearRemoteKeys() {
        remoteKeysDao.clearRemoteKeys()
    }

}