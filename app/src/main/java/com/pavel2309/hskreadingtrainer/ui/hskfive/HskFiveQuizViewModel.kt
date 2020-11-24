package com.pavel2309.hskreadingtrainer.ui.hskfive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pavel2309.hskreadingtrainer.data.QuizRepository
import com.pavel2309.hskreadingtrainer.data.db.QuizDatabase
import com.pavel2309.hskreadingtrainer.data.model.Category
import com.pavel2309.hskreadingtrainer.data.model.CategoryWithQuestionsAndAnswers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class HskFiveQuizViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizRepository

//    val getAllCategoryWithQuestionsAndAnswersHskFive: LiveData<List<CategoryWithQuestionsAndAnswers>>

    val getAllUnsolvedCategoryWithQuestionsAndAnswersHskFive: LiveData<List<CategoryWithQuestionsAndAnswers>>


    init {
        val quizDao = QuizDatabase.getDatabase(application).quizDao()
        repository = QuizRepository(quizDao)

//        getAllCategoryWithQuestionsAndAnswersHskFive =
//            repository.getAllCategoriesWithQuestionsAndAnswersByLevel(5)

        getAllUnsolvedCategoryWithQuestionsAndAnswersHskFive =
            repository.getAllUnsolvedCategoryWithQuestionsAndAnswersByLevel(5)
    }


    fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCategory(category)
        }
    }


    var categoryWithQuestionsAndAnswers: LiveData<List<CategoryWithQuestionsAndAnswers>> =
        repository.getCategoryWithQuestionsAndAnswersById(Random.nextLong(1, 76))

    fun updateQuestion() {
        categoryWithQuestionsAndAnswers =
            repository.getCategoryWithQuestionsAndAnswersById(Random.nextLong(1, 76))
    }


}