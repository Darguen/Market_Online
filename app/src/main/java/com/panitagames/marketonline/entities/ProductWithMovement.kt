package com.panitagames.marketonline.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithMovement(
    @Embedded val product : Product,
    @Relation(
        parentColumn = "id",
        entityColumn = "product_id"
    )
    val movements: List<MovementHistory>
)

