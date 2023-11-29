package com.panitagames.marketonline.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey (autoGenerate = true)val id: Int,
    @ColumnInfo(name = "product_type")val type: String?,
    @ColumnInfo(name = "product_description")val description: String?,
    @ColumnInfo(name = "product_name")val name: String?,
    @ColumnInfo(name = "product_price")val price: Int
) {

}