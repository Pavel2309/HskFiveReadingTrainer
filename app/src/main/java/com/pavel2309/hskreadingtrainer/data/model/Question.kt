package com.pavel2309.hskreadingtrainer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    val questionId: Long?,
    @ColumnInfo(name = "parent_category_id")
    val parentCategoryId: Long,
    val text: String
) {
    constructor(parentCategoryId: Long, text: String) : this(null, parentCategoryId, text)
}