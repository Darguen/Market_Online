package com.panitagames.marketonline.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


/*@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<Product>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(productIds: IntArray): List<Product>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Product

    @Insert
    fun insertAll(vararg products: Product)

    @Delete
    fun delete(product: Product)
}*/