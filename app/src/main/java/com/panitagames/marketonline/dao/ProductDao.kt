package com.panitagames.marketonline.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.panitagames.marketonline.entities.Product
import com.panitagames.marketonline.entities.ProductWithMovement


@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT id FROM Product WHERE id IN (:productId)")
    fun getId(productId :Int): Int

    @Query("SELECT * FROM Product WHERE id IN (:productIds)")
    fun loadAllByIds(productIds: IntArray): List<Product>


    @Query("SELECT * FROM Product ORDER BY product_name ASC")
    fun getAllProductsSortedByName(): List<Product>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(product: Product): Long

    @Transaction
    @Query("SELECT * from Product")
    fun getProductsWithPlayLists(): List<ProductWithMovement>

    @Delete
    fun delete(product: Product)

    @Update
    fun updateProduct(product: Product)

}