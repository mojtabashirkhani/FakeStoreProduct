package com.example.feature.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.model.ProductEntity
import com.example.data.remote.api.Resource
import com.example.data.remote.model.ProductResponse
import com.example.data.repo.ProductRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
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