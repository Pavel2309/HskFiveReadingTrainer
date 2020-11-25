package com.pavel2309.hskfivereadingtrainer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer_table")
data class Answer(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id")
    val answerId: Long?,
    @ColumnInfo(name = "parent_question_id")
    val parentQuestionId: Long,
    val text: String,
    val isCorrect: Boolean
) {
    constructor(parentQuestionId: Long, text: String, isCorrect: Boolean) : this(
        null,
        parentQuestionId,
        text,
        isCorrect
    )
}