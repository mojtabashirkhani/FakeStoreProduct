package com.example.domain


import com.example.network.api.Resource
import com.example.network.api.apiCall
import com.example.data.ProductRepository
import com.example.database.dao.ProductDao
import com.example.database.model.ProductEntity
import com.example.network.api.ProductApi
import com.example.network.model.ProductResponse
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