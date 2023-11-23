package com.panitagames.marketonline.Database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Database
import androidx.room.RoomDatabase
import com.panitagames.marketonline.DAO.ProductDao
import entities.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}