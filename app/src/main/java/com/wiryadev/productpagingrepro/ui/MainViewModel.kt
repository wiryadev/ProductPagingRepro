package com.wiryadev.productpagingrepro.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wiryadev.productpagingrepro.domain.GetProductsAsBuyerUseCase
import com.wiryadev.productpagingrepro.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsAsBuyerUseCase: GetProductsAsBuyerUseCase,
) : ViewModel() {

    val products: Flow<PagingData<Product>> = getProductsAsBuyerUseCase().cachedIn(viewModelScope)

}