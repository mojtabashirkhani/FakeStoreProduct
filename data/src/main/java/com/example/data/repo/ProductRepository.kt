package com.example.data.repo

import com.example.data.local.model.ProductEntity
import com.example.data.remote.api.Resource
import com.example.data.remote.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getRemoteProducts() : Flow<Resource<List<ProductResponse>>>
    suspend fun insertProductDetailToFavourite(productEntity: ProductEntity): Long
    suspend fun getProductFavorite(id: Long) : Flow<ProductEntity>?
}