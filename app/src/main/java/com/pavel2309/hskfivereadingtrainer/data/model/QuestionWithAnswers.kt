package com.pavel2309.hskfivereadingtrainer.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithAnswers(

    @Embedded
    val question: Question,

    @Relation(
        parentColumn = "question_id",
        entityColumn = "parent_question_id"
    )
    val answers: List<Answer>
)