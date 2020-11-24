package com.pavel2309.hskreadingtrainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pavel2309.hskreadingtrainer.R
import com.pavel2309.hskreadingtrainer.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding
    private lateinit var titleViewModel: TitleViewModel


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false)
//        reenterTransition = backward
//
//        val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true)
//        exitTransition = forward
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTitleBinding.inflate(inflater, container, false)

        titleViewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        binding.lifecycleOwner = this
        binding.titleViewModel = titleViewModel


        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(binding.navigationView)
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_contacts -> {
                    Toast.makeText(activity, "Contacts", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    false
                }
                R.id.nav_about -> {
                    Toast.makeText(activity, "About", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    false
                }
                else -> false
            }
        }


        binding.hskOneConstraint.setOnClickListener {
            //findNavController().navigate(R.id.action_titleFragment_to_hskOneFragment)
            //Toast.makeText(activity, "HSK 1 will come soon", Toast.LENGTH_SHORT).show()
        }
        binding.hskTwoConstraint.setOnClickListener {
            //findNavController().navigate(R.id.action_titleFragment_to_hskTwoFragment)
            //Toast.makeText(activity, "HSK 2 will come soon", Toast.LENGTH_SHORT).show()
        }
        binding.hskThreeConstraint.setOnClickListener {
            //findNavController().navigate(R.id.action_titleFragment_to_hskThreeFragment)
            //Toast.makeText(activity, "HSK 3 will come soon", Toast.LENGTH_SHORT).show()
        }
        binding.hskFourConstraint.setOnClickListener {
            //findNavController().navigate(R.id.action_titleFragment_to_hskFourFragment)
            //Toast.makeText(activity, "HSK 4 will come soon", Toast.LENGTH_SHORT).show()
        }
        binding.hskFiveConstraint.setOnClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_hskFiveFragment)
        }
        binding.hskSixConstraint.setOnClickListener {
            //findNavController().navigate(R.id.action_titleFragment_to_hskSixFragment)
            //Toast.makeText(activity, "HSK 6 will come soon", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

}