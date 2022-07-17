package com.wiryadev.productpagingrepro.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.wiryadev.productpagingrepro.data.repo.ProductRepository
import com.wiryadev.productpagingrepro.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductsAsBuyerUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {

    operator fun invoke(): Flow<PagingData<Product>> {
        return productRepository.getProductsAsBuyer().map { pagingData ->
            pagingData.map { it.mapToDomainModel() }
        }
    }

}