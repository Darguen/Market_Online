package com.panitagames.marketonline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.panitagames.marketonline.entities.MovementHistory


@Dao
interface MovementInfoDao {
    @Query("SELECT * FROM MovementHistory")
    fun getAll(): List<MovementHistory>

    @Query("SELECT * FROM MovementHistory WHERE product_id = :id")
    fun findByProduct(id: Int): List<MovementHistory>


    @Insert
    fun insertAll(vararg movements: MovementHistory)

}