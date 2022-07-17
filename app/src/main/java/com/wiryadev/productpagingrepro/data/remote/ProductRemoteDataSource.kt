package com.wiryadev.productpagingrepro.data.remote

import javax.inject.Inject

interface ProductRemoteDataSource {

    suspend fun getProductsAsBuyer(
        page: Int,
        size: Int,
    ): List<ProductDto>

}

class ProductRemoteDataSourceImpl @Inject constructor(
    private val service: ProductService,
) : ProductRemoteDataSource {

    override suspend fun getProductsAsBuyer(
        page: Int,
        size: Int,
    ): List<ProductDto> = service.getProductsAsBuyer(
        page = page,
        size = size,
    ).data

}