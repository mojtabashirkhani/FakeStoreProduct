package com.example.data.remote.api

import com.example.data.remote.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>
}