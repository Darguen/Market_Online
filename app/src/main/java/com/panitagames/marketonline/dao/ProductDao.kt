package com.panitagames.marketonline.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import entities.Product


@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM Product WHERE id IN (:productIds)")
    fun loadAllByIds(productIds: List<Int>): List<Product>

    @Query("SELECT * FROM Product ORDER BY :productNames ASC")
    fun sortByNames(productNames: List<String?>): List<Product>

    @Query("SELECT * FROM Product ORDER BY :productPrices ASC")
    fun sortByPrices(productPrices: List<Int>): List<Product>

    @Query("SELECT * FROM Product WHERE product_name LIKE :name LIMIT 1")
    fun findByName(name: String): Product

    @Query("SELECT * FROM Product ORDER BY product_name ASC")
    fun getAllProductsSortedByName(): List<Product>

    @Insert
    fun insertAll(products: Product)


    @Delete
    fun delete(product: Product)

    @Update
    fun updateProduct(product: Product)

}