package com.pavel2309.hskfivereadingtrainer.ui.hskfive

import android.app.Application
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.*
import com.pavel2309.hskfivereadingtrainer.data.QuizRepository
import com.pavel2309.hskfivereadingtrainer.data.db.QuizDatabase
import com.pavel2309.hskfivereadingtrainer.data.model.Category
import com.pavel2309.hskfivereadingtrainer.data.model.CategoryWithQuestionsAndAnswers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class HskFiveQuizViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizRepository

    //two minutes
    private val millisInFuture: Long = 60_000L
    //one second for every tick
    private val countDownInterval: Long = 1_000L
    var timeLeft = millisInFuture
    val editTime = MutableLiveData<String>()

    private var isRunning = false
    private var isPaused = false

    val getAllUnsolvedCategoryWithQuestionsAndAnswersHskFive: LiveData<List<CategoryWithQuestionsAndAnswers>>

    private lateinit var timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    private val currentTime: LiveData<Long>
        get() = _currentTime

    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        "Time left: " + DateUtils.formatElapsedTime(time)
    }


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


    private fun createTimer(leftTime: Long) {

        timer = object : CountDownTimer(leftTime, countDownInterval) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                _currentTime.value = timeLeft / 1000
            }
            override fun onFinish() {
                timer.cancel()
                isRunning = false
                isPaused = false
            }
        }

    }

    fun startTimer() {

        if(!isPaused && editTime.value != null && editTime.value != "") {
            timeLeft = editTime.value!!.toLong() * 1000 * 60

        } else if (!isPaused) {
            timeLeft =  millisInFuture
        }

        if(!isPaused && !isRunning && editTime.value == "") {
            timeLeft =  millisInFuture
        }

        if (!isRunning) {
            createTimer(timeLeft)
            timer.start()
            isRunning = true
        }
    }

    fun pauseTimer() {
        timer.cancel()
        isRunning = false
        isPaused = true
    }

    fun stopTimer() {
        timer.cancel()
        isRunning = false
        isPaused = false
    }


}