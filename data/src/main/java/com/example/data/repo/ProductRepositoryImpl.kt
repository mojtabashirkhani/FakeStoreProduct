package com.example.data.repo

import com.example.data.local.dao.ProductDao
import com.example.data.local.model.ProductEntity
import com.example.data.remote.api.ProductApi
import com.example.data.remote.api.Resource
import com.example.data.remote.api.apiCall
import com.example.data.remote.model.ProductResponse
import com.example.data.repo.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun getRemoteProducts(): Flow<Resource<List<ProductResponse>>> = flow {
        val response = apiCall(productApi) {
            getProducts()
        }
        emit(response)
    }.flowOn(Dispatchers.IO)



    override suspend fun insertProductDetailToFavourite(productEntity: ProductEntity): Long {
        return productDao.insertProductDetailToFavorite(productEntity)
    }

    override suspend fun getProductFavorite(id: Long): Flow<ProductEntity>? {
        return productDao.getProductById(id)
    }

}