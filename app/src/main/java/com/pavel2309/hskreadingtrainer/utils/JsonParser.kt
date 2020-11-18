package com.pavel2309.hskreadingtrainer.utils

import android.app.Application
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException

class JsonParser {

    fun getJsonDataFromAsset(application: Application, fileName: String): JsonInput? {

        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<JsonInput> = moshi.adapter(JsonInput::class.java)

        val jsonString: String
        try {
            jsonString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        return adapter.fromJson(jsonString)
    }


    @JsonClass(generateAdapter = true)
    data class JsonInput(
        val categories: List<Category>
    )

    @JsonClass(generateAdapter = true)
    data class Category(
        val level: Long,
        @Json(name = "category_text")
        val categoryText: String,
        val questions: List<Question>
    )

    @JsonClass(generateAdapter = true)
    data class Question(
        @Json(name = "question_text")
        val questionText: String,
        val answers: List<Answer>
    )

    @JsonClass(generateAdapter = true)
    data class Answer(
        val isCorrect: Boolean,
        @Json(name = "answer_text")
        val answerText: String
    )

}