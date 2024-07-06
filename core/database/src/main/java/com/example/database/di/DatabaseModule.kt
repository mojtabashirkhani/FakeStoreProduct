package com.example.database.di

import android.app.Application
import androidx.room.Room
import com.example.common.DB_NAME
import com.example.database.ProductDatabase
import com.example.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): ProductDatabase {
        return Room
            .databaseBuilder(application, ProductDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductDao(db: ProductDatabase): ProductDao {
        return db.productDao()
    }
}