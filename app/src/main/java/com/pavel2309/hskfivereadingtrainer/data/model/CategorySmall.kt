package com.pavel2309.hskfivereadingtrainer.data.model

import androidx.room.ColumnInfo

data class CategorySmall(
    @ColumnInfo(name = "category_id")
    val categoryId: Long?,

    val isSolved: Boolean
)