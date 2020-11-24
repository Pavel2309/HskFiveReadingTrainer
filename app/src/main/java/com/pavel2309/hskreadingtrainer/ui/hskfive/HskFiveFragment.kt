package com.pavel2309.hskreadingtrainer.ui.hskfive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.pavel2309.hskreadingtrainer.R
import com.pavel2309.hskreadingtrainer.databinding.FragmentHskFiveBinding

class HskFiveFragment : Fragment() {

    private lateinit var binding: FragmentHskFiveBinding
    private lateinit var hskFiveViewModel: HskFiveViewModel

    private var isFinished = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHskFiveBinding.inflate(inflater, container, false)

        hskFiveViewModel = ViewModelProvider(this).get(HskFiveViewModel::class.java)
        binding.lifecycleOwner = this
        binding.hskFiveViewModel = hskFiveViewModel


        binding.startButton.setOnClickListener { view: View ->
            if(isFinished != 0) {
                view.findNavController().navigate(R.id.action_hskFiveFragment_to_hskFiveQuizFragment)
            } else {
                Toast.makeText(activity, "You have finished all questions", Toast.LENGTH_SHORT).show()
            }

        }


        binding.topAppBar.setNavigationOnClickListener {
            //findNavController().navigate(R.id.action_hskFiveFragment_to_titleFragment)
            findNavController().popBackStack()
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.topAppBarAddQuestion -> {

                    //findNavController().navigate(R.id.action_hskFiveFragment_to_addQuestionFragment)

                    true
                }
                R.id.topAppBarInflate -> {

                    hskFiveViewModel.prepopulateDataFromJson("H50000_reading.json")
                    hskFiveViewModel.prepopulateDataFromJson("H51001_reading.json")
                    hskFiveViewModel.prepopulateDataFromJson("H51002_reading.json")
                    hskFiveViewModel.prepopulateDataFromJson("H51003_reading.json")

                    true
                }
                R.id.topAppBarClearAll -> {

                    hskFiveViewModel.deleteAll()

                    true
                }
                R.id.topAppBarResetProgress -> {

                    resetQuestions()

                    true
                }
                else -> false
            }
        }



        hskFiveViewModel.allUnsolevedCategoriesWithQuestionsAndAnswersByLevel.observe(viewLifecycleOwner) {

            isFinished = it.size

            binding.testTextView.text = isFinished.toString()

        }

        hskFiveViewModel.allCategoriesIdHskFive.observe(viewLifecycleOwner) {

            binding.titleTextView.text = it.size.toString()

            binding.titleTextView.visibility = View.VISIBLE





        }

//        hskFiveViewModel.categoryWithQuestionsAndAnswers.observe(viewLifecycleOwner) {
//
//            binding.titleTextView.text = it[0].category.text
//
//            binding.titleTextView.visibility = View.VISIBLE
//
//        }


        return binding.root
    }


    private fun resetQuestions() {

        hskFiveViewModel.allCategoriesWithQuestionsAndAnswersByLevel.observe(viewLifecycleOwner, {

            for(i in it) {
                i.category.isSolved = false
                binding.hskFiveViewModel?.updateCategory(i.category)
            }

        })
    }
}