package com.pavel2309.hskreadingtrainer.ui.hskfive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavel2309.hskreadingtrainer.R
import com.pavel2309.hskreadingtrainer.adapters.QuestionAdapter
import com.pavel2309.hskreadingtrainer.data.model.CategoryWithQuestionsAndAnswers
import com.pavel2309.hskreadingtrainer.databinding.FragmentHskFiveQuizBinding

class HskFiveQuizFragment : Fragment() {


    private lateinit var adapter: QuestionAdapter

    private lateinit var binding: FragmentHskFiveQuizBinding
    private lateinit var hskFiveQuizViewModel: HskFiveQuizViewModel

    private lateinit var currentQuestion: CategoryWithQuestionsAndAnswers

    private var unsolvedQuestions = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        binding = FragmentHskFiveQuizBinding.inflate(inflater, container, false)

        hskFiveQuizViewModel = ViewModelProvider(this).get(HskFiveQuizViewModel::class.java)

        binding.lifecycleOwner = this
        binding.hskFiveQuizViewModel = hskFiveQuizViewModel

        val recyclerView = binding.recyclerView
        adapter = context?.let { QuestionAdapter(it) }!!
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        binding.topAppBarQuizHsk5.setNavigationOnClickListener {
            findNavController().popBackStack()
        }


        hskFiveQuizViewModel.getAllUnsolvedCategoryWithQuestionsAndAnswersHskFive.observe(
            viewLifecycleOwner,
            {

                if (it.isNotEmpty()) {

                    currentQuestion = it[0]
                    setCurrentQuestion()
                } else {
                    quizFinished()
                }


            })


        binding.testButton.setOnClickListener {
            questionSolved()
        }

        return binding.root
    }

    private fun setCurrentQuestion() {

        if (unsolvedQuestions) {
            binding.categoryTextView.setText(currentQuestion.category.text)

            binding.categoryTextView.setText(currentQuestion.category.text)


            binding.categoryTextView.visibility = View.VISIBLE
            binding.testButton.visibility = View.VISIBLE
            binding.scrollViewQuiz.smoothScrollTo(0, 0)

            currentQuestion.let {
                adapter.setQuestions(currentQuestion.questions)
            }
        } else {
            quizFinished()
        }

    }

    private fun questionSolved() {
        val myQuestion = currentQuestion
        myQuestion.category.isSolved = true
        binding.hskFiveQuizViewModel?.updateCategory(myQuestion.category)
    }


    private fun quizFinished() {
        findNavController().navigate(R.id.action_hskFiveQuizFragment_to_finishFragment)
    }

}