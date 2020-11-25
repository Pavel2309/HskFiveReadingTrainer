package com.pavel2309.hskfivereadingtrainer.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pavel2309.hskfivereadingtrainer.data.model.*

@Dao
interface QuizDao {

    @Query("SELECT * from category_table ORDER BY category_id")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * from answer_table ORDER BY parent_question_id")
    fun getAllAnswers(): LiveData<List<Answer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: Answer): Long

    @Update
    suspend fun updateCategory(category: Category)

    @Query("DELETE FROM category_table")
    suspend fun deleteAllCategory()

    @Query("DELETE FROM question_table")
    suspend fun deleteAllQuestion()

    @Query("DELETE FROM answer_table")
    suspend fun deleteAllAnswer()


    @Transaction
    @Query("SELECT * FROM question_table WHERE parent_category_id = :categoryId ")
    fun getQuestionWithAnswers(categoryId: Long): LiveData<List<QuestionWithAnswers>>

    @Transaction
    @Query("SELECT * FROM category_table WHERE category_id = :categoryId")
    fun getCategoryWithQuestionsAndAnswersById(categoryId: Long): LiveData<List<CategoryWithQuestionsAndAnswers>>

    @Transaction
    @Query("SELECT * FROM category_table")
    fun getCategoryWithQuestionsAndAnswers(): LiveData<List<CategoryWithQuestionsAndAnswers>>

    @Query("SELECT category_id, isSolved from category_table")
    fun getAllCategoriesId(): LiveData<List<CategorySmall>>

    @Query("SELECT category_id, isSolved from category_table WHERE level = :level")
    fun getAllCategoriesIdByLevel(level: Long): LiveData<List<CategorySmall>>

    @Transaction
    @Query("SELECT * FROM category_table WHERE level = :level")
    fun getCategoryWithQuestionsAndAnswersByLevel(level: Long): LiveData<List<CategoryWithQuestionsAndAnswers>>

    @Transaction
    @Query("SELECT * FROM category_table WHERE level = :level AND isSolved = 0")
    fun getUnsolvedCategoryWithQuestionsAndAnswersByLevel(level: Long): LiveData<List<CategoryWithQuestionsAndAnswers>>


}