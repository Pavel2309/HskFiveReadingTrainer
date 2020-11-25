package com.pavel2309.hskfivereadingtrainer.ui.hskfive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pavel2309.hskfivereadingtrainer.data.QuizRepository
import com.pavel2309.hskfivereadingtrainer.data.db.QuizDatabase
import com.pavel2309.hskfivereadingtrainer.data.model.Category
import com.pavel2309.hskfivereadingtrainer.data.model.CategoryWithQuestionsAndAnswers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class HskFiveQuizViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizRepository

    val getAllUnsolvedCategoryWithQuestionsAndAnswersHskFive: LiveData<List<CategoryWithQuestionsAndAnswers>>


    init {
        val quizDao = QuizDatabase.getDatabase(application).quizDao()
        repository = QuizRepository(quizDao)

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