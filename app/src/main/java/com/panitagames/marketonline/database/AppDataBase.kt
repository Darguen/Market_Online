package com.panitagames.marketonline.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.panitagames.marketonline.dao.MovementInfoDao
import com.panitagames.marketonline.dao.ProductDao
import com.panitagames.marketonline.entities.MovementHistory
import com.panitagames.marketonline.entities.Product

@Database(entities = [Product::class, MovementHistory::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun movementDao(): MovementInfoDao
}