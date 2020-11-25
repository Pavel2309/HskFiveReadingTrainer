package com.pavel2309.hskfivereadingtrainer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val categoryId: Long?,
    val level: Long,
    var text: String,
    var isSolved: Boolean
) {
    constructor(level: Long, text: String, isSolved: Boolean) : this(null, level, text, false)
}