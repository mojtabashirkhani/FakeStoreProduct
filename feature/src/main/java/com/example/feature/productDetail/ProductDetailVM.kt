package com.example.feature.productDetail

import androidx.lifecycle.ViewModel
import com.example.database.model.ProductEntity
import com.example.domain.ProductRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailVM @Inject constructor(private val productRepository: ProductRepositoryImpl): ViewModel() {

    val productStateFlow =
        MutableStateFlow(ProductEntity())

    suspend fun insertProductToFavourite(productEntity: ProductEntity): Long {
        return productRepository.insertProductDetailToFavourite(productEntity)
    }

    suspend fun getProductFavourite(id: Long) {
        productRepository.getProductFavorite(id)?.collect {
            if (it != null)
            productStateFlow.emit(it)
        }
    }

}