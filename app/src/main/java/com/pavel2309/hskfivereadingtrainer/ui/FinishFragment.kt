package com.pavel2309.hskfivereadingtrainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pavel2309.hskfivereadingtrainer.R
import com.pavel2309.hskfivereadingtrainer.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {

    private lateinit var binding: FragmentFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFinishBinding.inflate(inflater, container, false)

        binding.topAppBarQuizHsk5Finish.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_finishFragment_to_hskFiveFragment)
        }

        return binding.root
    }


}