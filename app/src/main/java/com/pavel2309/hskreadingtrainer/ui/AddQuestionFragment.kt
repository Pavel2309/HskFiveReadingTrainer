package com.pavel2309.hskreadingtrainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pavel2309.hskreadingtrainer.databinding.FragmentAddQuestionBinding

class AddQuestionFragment : Fragment() {

    private lateinit var binding: FragmentAddQuestionBinding
    private lateinit var addQuestionViewModel: AddQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddQuestionBinding.inflate(inflater, container, false)

        addQuestionViewModel = ViewModelProvider(this).get(AddQuestionViewModel::class.java)
        binding.lifecycleOwner = this
        binding.addQuestionViewModel = addQuestionViewModel



        binding.addQuestionButton.setOnClickListener {
            addCategory()
        }

        binding.addAnotherQuestionButton.setOnClickListener {
            addQuestion()
        }

        binding.addAnswersButton.setOnClickListener {
            addAnswer()

            binding.enterQuestionText.text.clear()
            binding.enterAnswerOneText.text.clear()
            binding.enterAnswerTwoText.text.clear()
            binding.enterAnswerThreeText.text.clear()
            binding.enterAnswerFourText.text.clear()
        }


        return binding.root
    }

    private fun addCategory() {
        if (binding.enterCategoryText.text.isNotEmpty() && binding.levelTextView.text.isNotEmpty()) {
            addQuestionViewModel.addCategory(binding.levelTextView.text.toString().toLong(), binding.enterCategoryText.text.toString())
        }
    }

    private fun addQuestion() {
        if (binding.enterQuestionText.text.isNotEmpty()) {

            addQuestionViewModel.addQuestion(binding.enterQuestionText.text.toString())

        }
    }

    private fun addAnswer() {
        if (binding.enterAnswerOneText.text.isNotEmpty() &&
            binding.enterAnswerTwoText.text.isNotEmpty() &&
            binding.enterAnswerThreeText.text.isNotEmpty() &&
            binding.enterAnswerFourText.text.isNotEmpty()
        ) {

            addQuestionViewModel.addAnswer(
                binding.enterAnswerOneText.text.toString(),
                binding.isOneCorrectCheckBox.isChecked
            )
            addQuestionViewModel.addAnswer(
                binding.enterAnswerTwoText.text.toString(),
                binding.isTwoCorrectCheckBox.isChecked
            )
            addQuestionViewModel.addAnswer(
                binding.enterAnswerThreeText.text.toString(),
                binding.isThreeCorrectCheckBox.isChecked
            )
            addQuestionViewModel.addAnswer(
                binding.enterAnswerFourText.text.toString(),
                binding.isFourCorrectCheckBox.isChecked
            )

        }
    }


}