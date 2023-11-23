package com.panitagames.marketonline.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import entities.Product


@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM Product WHERE id IN (:productIds)")
    fun loadAllByIds(productIds: IntArray): List<Product>

    @Query("SELECT * FROM Product WHERE product_name LIKE :name LIMIT 1")
    fun findByName(name: String): Product

    @Insert
    fun insertAll(vararg products: MutableList<Product>)

    @Delete
    fun delete(product: Product)
}