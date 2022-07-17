package com.wiryadev.productpagingrepro.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.wiryadev.productpagingrepro.domain.model.Product

@Dao
interface ProductCacheDao {

    @Query("SELECT * FROM product_cache")
    fun getCachedProducts(): PagingSource<Int, ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(products: List<ProductEntity>)

    @Query("DELETE FROM product_cache")
    suspend fun deleteAll()

}

@Entity(tableName = "product_cache")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    val price: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "location")
    val location: String?,

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "category")
    val category: String,
) {
    fun mapToDomainModel() = Product(
        id = id,
        name = name,
        description = description,
        price = price,
        imageUrl = imageUrl,
        location = location.orEmpty(),
        userId = userId,
        category = category,
    )
}