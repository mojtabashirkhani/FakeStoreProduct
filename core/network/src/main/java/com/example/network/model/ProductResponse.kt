package com.example.network.model

import com.example.network.model.Rating
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id") var id: Long? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("rating") var rating: Rating? = Rating()
)
