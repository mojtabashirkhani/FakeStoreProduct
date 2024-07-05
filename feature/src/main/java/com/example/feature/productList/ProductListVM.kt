package com.example.feature.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.api.Resource
import com.example.data.remote.model.ProductResponse
import com.example.data.repo.ProductRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListVM @Inject constructor(private val productRepository: ProductRepositoryImpl): ViewModel() {
    val productListStateFlow =
        MutableStateFlow<Resource<List<ProductResponse>>>(Resource.loading())

    init {
        viewModelScope.launch {
            getProductList()
        }
    }
    private suspend fun getProductList() {
        productRepository.getRemoteProducts().collect {
                if (it.isLoading())
                    productListStateFlow.emit(Resource.loading())
                else if (it.isSuccess())
                    productListStateFlow.emit(Resource.success(it.data?.sortedBy { it.price }))
                else
                    productListStateFlow.emit(Resource.error(it.message))
            }

    }

}