package com.wiryadev.productpagingrepro.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wiryadev.productpagingrepro.data.local.ProductEntity
import com.wiryadev.productpagingrepro.data.local.ProductLocalDataSource
import com.wiryadev.productpagingrepro.data.remote.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProductRepository {

    fun getProductsAsBuyer(): Flow<PagingData<ProductEntity>>

}

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource,
) : ProductRepository {
    @ExperimentalPagingApi
    override fun getProductsAsBuyer(): Flow<PagingData<ProductEntity>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator = ProductRemoteMediator(
                remoteDataSource = remoteDataSource,
                localDataSource = localDataSource,
            ),
            pagingSourceFactory = {
                localDataSource.getCachedProducts()
            }
        ).flow
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 20
    }
}