package com.panitagames.marketonline.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.panitagames.marketonline.dao.ProductDao
import entities.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}