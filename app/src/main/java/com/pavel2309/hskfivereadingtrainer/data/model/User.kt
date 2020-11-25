package com.pavel2309.hskfivereadingtrainer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val userId: Long?,
    val name: String,
) {
    constructor(name: String) : this(null, name)
}