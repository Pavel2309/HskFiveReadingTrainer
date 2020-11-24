package com.pavel2309.hskreadingtrainer.ui.hskfive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pavel2309.hskreadingtrainer.data.QuizRepository
import com.pavel2309.hskreadingtrainer.data.db.QuizDatabase
import com.pavel2309.hskreadingtrainer.data.model.*
import com.pavel2309.hskreadingtrainer.util.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class HskFiveViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizRepository
    val allCategoriesIdHskFive: LiveData<List<CategorySmall>>

    private var currentTime = MutableLiveData<List<CategorySmall>>()

    val allCategoriesWithQuestionsAndAnswersByLevel: LiveData<List<CategoryWithQuestionsAndAnswers>>

    val allUnsolevedCategoriesWithQuestionsAndAnswersByLevel: LiveData<List<CategoryWithQuestionsAndAnswers>>



    init {
        val quizDao = QuizDatabase.getDatabase(application).quizDao()
        repository = QuizRepository(quizDao)
        allCategoriesIdHskFive = repository.getAllCategoriesIdByLevel(5)

        allCategoriesWithQuestionsAndAnswersByLevel = repository.getAllCategoriesWithQuestionsAndAnswersByLevel(5)

        allUnsolevedCategoriesWithQuestionsAndAnswersByLevel = repository.getAllUnsolvedCategoryWithQuestionsAndAnswersByLevel(5)
    }


    fun something(): Long? {
        return currentTime.value?.get(Random.nextInt(0, currentTime.value!!.size))?.categoryId
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

                    categoryId = repository.insertCategory(Category(category.level ,category.categoryText, false))


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