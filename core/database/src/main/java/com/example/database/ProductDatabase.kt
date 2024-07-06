package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.common.DB_VERSION
import com.example.database.dao.ProductDao
import com.example.database.model.ProductEntity


@Database(
    entities = [ProductEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}