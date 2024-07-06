package com.example.data


import com.example.network.api.Resource
import com.example.database.model.ProductEntity
import com.example.network.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getRemoteProducts() : Flow<Resource<List<ProductResponse>>>
    suspend fun insertProductDetailToFavourite(productEntity: ProductEntity): Long
    suspend fun getProductFavorite(id: Long) : Flow<ProductEntity>?
}