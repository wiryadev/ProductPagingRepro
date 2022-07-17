package com.wiryadev.productpagingrepro.data.remote

import com.wiryadev.productpagingrepro.data.remote.AuthInterceptor.Companion.NO_AUTH_HEADER_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProductService {

    @GET("buyer/product?status=available")
    @Headers("${NO_AUTH_HEADER_KEY}: true")
    suspend fun getProductsAsBuyer(
        @Query("page") page: Int,
        @Query("per_page") size: Int,
        @Query("category_id") categoryId: Int? = null,
        @Query("search") search: String? = null,
    ): PagedProductsDto

}