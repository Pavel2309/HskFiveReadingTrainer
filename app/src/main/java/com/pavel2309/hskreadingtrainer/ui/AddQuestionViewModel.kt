package com.pavel2309.hskreadingtrainer.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pavel2309.hskreadingtrainer.data.QuizRepository
import com.pavel2309.hskreadingtrainer.data.db.QuizDatabase
import com.pavel2309.hskreadingtrainer.data.model.Answer
import com.pavel2309.hskreadingtrainer.data.model.Category
import com.pavel2309.hskreadingtrainer.data.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddQuestionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizRepository
    private var myCategoryId = 0L
    private var mtQuestionId = 0L


    private val getAllCategories: LiveData<List<Category>>

    init {
        val quizDao = QuizDatabase.getDatabase(application).quizDao()
        repository = QuizRepository(quizDao)
        getAllCategories = repository.getAllCategories
    }

    fun addCategory(level: Long, categoryText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            myCategoryId = repository.insertCategory(Category(level, categoryText, false))
        }
    }

    fun addQuestion(questionText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mtQuestionId = repository.insertQuestion(Question(myCategoryId, questionText))
        }
    }

    fun addAnswer(answerText: String, isCorrect: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAnswer(Answer(mtQuestionId, answerText, isCorrect))
        }
    }

}