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

    private lateinit var randomQuestion: CategoryWithQuestionsAndAnswers

    private lateinit var currentQuestion: CategoryWithQuestionsAndAnswers

    private var unsolvedQuestions = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = FragmentHskFiveQuizBinding.inflate(inflater, container, false)

        hskFiveQuizViewModel = ViewModelProvider(this).get(HskFiveQuizViewModel::class.java)

        binding.lifecycleOwner = this
        binding.hskFiveQuizViewModel = hskFiveQuizViewModel

        val recyclerView = binding.recyclerView
        adapter = context?.let { QuestionAdapter(it) }!!
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        binding.topAppBarQuizHsk5.setNavigationOnClickListener {
            //findNavController().navigate(R.id.action_hskFiveQuizFragment_to_hskFiveFragment)
            findNavController().popBackStack()
        }

//        binding.topAppBarQuizHsk5.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.topAppBarHsk5ChangeText -> {
//
//                    binding.categoryTextView.isEnabled = true
//
//                    true
//                }
//                R.id.topAppBarHsk5Save -> {
//
//                    updateQuestion()
//
//                    true
//                }
//                else -> false
//            }
//        }



        hskFiveQuizViewModel.getAllUnsolvedCategoryWithQuestionsAndAnswersHskFive.observe(
            viewLifecycleOwner,
            {

                if (it.isNotEmpty()) {

                    randomQuestion = it.random()
                    getRandomQuestion()
                } else {
                    quizFinished()
                }


            })

//        hskFiveQuizViewModel.getAllCategoryWithQuestionsAndAnswersHskFive.observe(
//            viewLifecycleOwner,
//            {
//
//                for (i in it) {
//                    if (!i.category.isSolved) {
//                        randomQuestion = i
//                    }
//                }
//
//                getRandomQuestion()
//            })


        //getCurrentQuestion()

        //getRandomQuestion()


        binding.testButton.setOnClickListener {
//            hskFiveQuizViewModel.updateQuestion()
//            getCurrentQuestion()

            questionSolved()

            //getRandomQuestion()

//            //getRandomQuestion()
//            if(::randomQuestion.isInitialized) {
//                questionSolved()
//            }


        }


        return binding.root
    }

    private fun getRandomQuestion() {

//        hskFiveQuizViewModel.getAllCategoryWithQuestionsAndAnswersHskFive.observe(viewLifecycleOwner, {
//
//            randomQuestion = it.random()
//
//            //binding.categoryTextView.text = randomQuestion.category.text
//
//            binding.categoryTextView.setText(randomQuestion.category.text)
//
//
//            binding.categoryTextView.visibility = View.VISIBLE
//            binding.testButton.visibility = View.VISIBLE
//            binding.scrollViewQuiz.smoothScrollTo(0, 0)
//
//            it.let {
//                adapter.setQuestions(randomQuestion.questions)
//            }
//
//        })

        if (unsolvedQuestions) {
            binding.categoryTextView.setText(randomQuestion.category.text)

            binding.categoryTextView.setText(randomQuestion.category.text)


            binding.categoryTextView.visibility = View.VISIBLE
            binding.testButton.visibility = View.VISIBLE
            binding.scrollViewQuiz.smoothScrollTo(0, 0)

            randomQuestion.let {
                adapter.setQuestions(randomQuestion.questions)
            }
        } else {
            quizFinished()
        }


        //binding.categoryTextView.isEnabled = false
        //binding.categoryTextView.inputType = TYPE_NULL
    }

    private fun getCurrentQuestion() {

        hskFiveQuizViewModel.categoryWithQuestionsAndAnswers.observe(viewLifecycleOwner) {

            currentQuestion = it[0]

            binding.categoryTextView.setText(currentQuestion.category.text)

            binding.categoryTextView.visibility = View.VISIBLE
            binding.testButton.visibility = View.VISIBLE
            binding.scrollViewQuiz.smoothScrollTo(0, 0)

            it.let {
                adapter.setQuestions(it[0].questions)
            }

        }
    }

    private fun updateQuestion() {

        val question = currentQuestion

        question.category.text = binding.categoryTextView.text.toString()

        binding.hskFiveQuizViewModel?.updateCategory(question.category)

    }


    private fun questionSolved() {
        val myQuestion = randomQuestion
        myQuestion.category.isSolved = true
        binding.hskFiveQuizViewModel?.updateCategory(myQuestion.category)
    }


    private fun quizFinished() {
        findNavController().navigate(R.id.action_hskFiveQuizFragment_to_finishFragment)
    }

}