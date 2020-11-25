package com.pavel2309.hskfivereadingtrainer.ui.hskfive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pavel2309.hskfivereadingtrainer.data.QuizRepository
import com.pavel2309.hskfivereadingtrainer.data.db.QuizDatabase
import com.pavel2309.hskfivereadingtrainer.data.model.*
import com.pavel2309.hskfivereadingtrainer.util.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HskFiveViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizRepository
    val allCategoriesIdHskFive: LiveData<List<CategorySmall>>


    val allCategoriesWithQuestionsAndAnswersByLevel: LiveData<List<CategoryWithQuestionsAndAnswers>>

    val allUnsolevedCategoriesWithQuestionsAndAnswersByLevel: LiveData<List<CategoryWithQuestionsAndAnswers>>


    init {
        val quizDao = QuizDatabase.getDatabase(application).quizDao()
        repository = QuizRepository(quizDao)
        allCategoriesIdHskFive = repository.getAllCategoriesIdByLevel(5)

        allCategoriesWithQuestionsAndAnswersByLevel =
            repository.getAllCategoriesWithQuestionsAndAnswersByLevel(5)

        allUnsolevedCategoriesWithQuestionsAndAnswersByLevel =
            repository.getAllUnsolvedCategoryWithQuestionsAndAnswersByLevel(5)
    }


    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCategory(category)
        }
    }


    fun prepopulateDataFromJson(filename: String) {

        var categoryId: Long
        var questionId: Long

        val categories = JsonParser().getJsonDataFromAsset(getApplication(), filename)

        if (categories != null) {
            viewModelScope.launch(Dispatchers.IO) {
                for (category in categories.categories) {

                    categoryId = repository.insertCategory(
                        Category(
                            category.level,
                            category.categoryText,
                            false
                        )
                    )


                    for (question in category.questions) {

                        questionId =
                            repository.insertQuestion(Question(categoryId, question.questionText))


                        for (answer in question.answers) {

                            repository.insertAnswer(
                                Answer(
                                    questionId,
                                    answer.answerText,
                                    answer.isCorrect
                                )
                            )
                        }
                    }
                }
            }
        }
    }

}