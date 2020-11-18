package com.pavel2309.hskreadingtrainer.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pavel2309.hskreadingtrainer.data.model.Answer
import com.pavel2309.hskreadingtrainer.data.model.Category
import com.pavel2309.hskreadingtrainer.data.model.Question

@Database(
    entities = [(Category::class), (Question::class), (Answer::class)],
    version = 1,
    exportSchema = false
)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object {

        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "hsk_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}