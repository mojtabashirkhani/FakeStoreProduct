package com.example.network.api

import com.example.network.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>
}