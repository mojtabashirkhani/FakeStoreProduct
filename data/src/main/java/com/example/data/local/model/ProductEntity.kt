package com.example.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductFavorite")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: Long,
    @ColumnInfo("isFavorite") var isFavorite: Boolean? = true,
)
