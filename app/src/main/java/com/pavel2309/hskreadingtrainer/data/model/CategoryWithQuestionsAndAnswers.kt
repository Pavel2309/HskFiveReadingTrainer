package com.pavel2309.hskreadingtrainer.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithQuestionsAndAnswers(

    @Embedded
    val category: Category,

    @Relation(
        entity = Question::class,
        parentColumn = "category_id",
        entityColumn = "parent_category_id"
    )
    val questions: List<QuestionWithAnswers>
)