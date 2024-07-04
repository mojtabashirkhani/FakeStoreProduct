package com.example.feature.productDetail

import androidx.lifecycle.ViewModel
import com.example.data.local.model.ProductEntity
import com.example.data.repo.ProductRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailVM @Inject constructor(private val productRepository: ProductRepositoryImpl): ViewModel() {

    suspend fun insertProductToFavourite(productEntity: ProductEntity) {
        productRepository.insertProductDetailToFavourite(productEntity)
    }

    suspend fun getProductFavourite(id: Long) {
        productRepository.getProductFavorite(id)
    }

    suspend fun deleteProductFromFavourite(productEntity: ProductEntity) {
        productRepository.deleteProductDetailFromFavourite(productEntity)
    }
}