package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.data.DB_VERSION
import com.example.data.local.dao.ProductDao
import com.example.data.local.model.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}