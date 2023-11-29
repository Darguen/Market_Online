package com.panitagames.marketonline.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovementHistory (
    @PrimaryKey(autoGenerate = true) val movementId: Int,
    @ColumnInfo(name = "product_id") val pId: Int,
    @ColumnInfo(name = "product_name") val productName: String?,
    @ColumnInfo(name = "movement_type") val movementType: String?
)