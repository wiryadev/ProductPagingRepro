package com.wiryadev.productpagingrepro.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wiryadev.productpagingrepro.data.local.ProductEntity
import com.wiryadev.productpagingrepro.data.local.SecondhandDatabase
import com.wiryadev.productpagingrepro.data.remote.ProductService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProductRepository {

    fun getProductsAsBuyer(): Flow<PagingData<ProductEntity>>

}

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductService,
    private val database: SecondhandDatabase,
) : ProductRepository {
    @ExperimentalPagingApi
    override fun getProductsAsBuyer(): Flow<PagingData<ProductEntity>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator = ProductRemoteMediator(
                apiService = apiService,
                database = database,
            ),
            pagingSourceFactory = {
                database.productCacheDao().getCachedProducts()
            }
        ).flow
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 20
    }
}