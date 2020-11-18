package com.pavel2309.hskreadingtrainer.data

import androidx.lifecycle.LiveData
import com.pavel2309.hskreadingtrainer.data.db.QuizDao
import com.pavel2309.hskreadingtrainer.data.model.*

class QuizRepository(private val quizDao: QuizDao) {

    val getAllCategories: LiveData<List<Category>> = quizDao.getAllCategories()


    suspend fun insertCategory(category: Category): Long {
        return quizDao.insertCategory(category)
    }

    suspend fun insertQuestion(question: Question): Long {
        return quizDao.insertQuestion(question)
    }

    suspend fun insertAnswer(answer: Answer): Long {
        return quizDao.insertAnswer(answer)
    }

    suspend fun updateCategory(category: Category) {
        return quizDao.updateCategory(category)
    }

    suspend fun deleteAll() {
        quizDao.deleteAllCategory()
        quizDao.deleteAllQuestion()
        quizDao.deleteAllAnswer()
    }

    fun getAllCategoriesIdByLevel(level: Long): LiveData<List<CategorySmall>> {
        return quizDao.getAllCategoriesIdByLevel(level)
    }

    fun getAllCategoriesWithQuestionsAndAnswersByLevel(level: Long): LiveData<List<CategoryWithQuestionsAndAnswers>> {
        return quizDao.getCategoryWithQuestionsAndAnswersByLevel(level)
    }

    fun getAllUnsolvedCategoryWithQuestionsAndAnswersByLevel(level: Long): LiveData<List<CategoryWithQuestionsAndAnswers>> {
        return quizDao.getUnsolvedCategoryWithQuestionsAndAnswersByLevel(level)
    }

    fun getCategoryWithQuestionsAndAnswersById(categoryId: Long): LiveData<List<CategoryWithQuestionsAndAnswers>> {
        return quizDao.getCategoryWithQuestionsAndAnswersById(categoryId)
    }

}