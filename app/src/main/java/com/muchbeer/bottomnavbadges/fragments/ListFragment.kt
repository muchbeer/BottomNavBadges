package com.muchbeer.bottomnavbadges.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muchbeer.bottomnavbadges.R
import com.muchbeer.bottomnavbadges.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)


        return binding.root
    }


}