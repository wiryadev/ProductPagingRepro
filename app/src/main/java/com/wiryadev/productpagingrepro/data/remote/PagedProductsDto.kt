package com.wiryadev.productpagingrepro.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.wiryadev.productpagingrepro.data.local.ProductEntity
import com.wiryadev.productpagingrepro.domain.model.Product

@JsonClass(generateAdapter = true)
data class PagedProductsDto(
    @Json(name = "page")
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "data")
    val data: List<ProductDto>,
)

@JsonClass(generateAdapter = true)
data class ProductDto(
    @Json(name = "base_price")
    val basePrice: Int?,
    @Json(name = "Categories")
    val categories: List<CategoryDto>,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_name")
    val imageName: String?,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "user_id")
    val userId: Int,
) {
    fun mapToDomainModel() = Product(
        id = id,
        name = name.orEmpty(),
        description = description.orEmpty(),
        price = basePrice ?: 0,
        imageUrl = imageUrl.orEmpty(),
        location = location.orEmpty(),
        userId = userId,
        category = try {
            categories[0].name
        } catch (e: Exception) {
            "No Categories!"
        }
    )

    fun mapToEntityModel() = ProductEntity(
        id = id,
        name = name.orEmpty(),
        description = description.orEmpty(),
        price = basePrice ?: 0,
        imageUrl = imageUrl.orEmpty(),
        location = location.orEmpty(),
        userId = userId,
        category = try {
            categories[0].name
        } catch (e: Exception) {
            ""
        },
    )
}

@JsonClass(generateAdapter = true)
data class CategoryDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
)