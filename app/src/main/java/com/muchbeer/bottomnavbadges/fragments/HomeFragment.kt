package com.muchbeer.bottomnavbadges.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.muchbeer.bottomnavbadges.BadgeViewModel
import com.muchbeer.bottomnavbadges.R
import com.muchbeer.bottomnavbadges.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : FragmentHomeBinding
    private val badgeViewModel : BadgeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        ArrayAdapter.createFromResource(requireContext(),
                                        R.array.badges_data_array,
                                        android.R.layout.simple_spinner_item).also { adapter->
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            binding.dataSpinner.adapter = adapter
                         }

        binding.apply {

            badgeViewModel.spinnerSelected.observe(viewLifecycleOwner) { itemSelected ->
                Log.d("HomeFragment", "Observed item selected is : ${itemSelected}")
                if (itemSelected=="Home") {
                    addButton.setOnClickListener {
                        onHomeClicked(inputCount.text.toString().toInt())
                }
            } else if (itemSelected == "List") {
                    addButton.setOnClickListener {
                        onListClicked(inputCount.text.toString().toInt())
                    }
                } else if (itemSelected == "Profile") {
                    addButton.setOnClickListener {
                        onProfileClicked(inputCount.text.toString().toInt())
                    }
                }
            }

            removeButton.setOnClickListener {
                badgeViewModel.spinnerSelected.observe(viewLifecycleOwner) {
                    if (it == "Home") {
                        badgeViewModel.buttonSelected("Home")
                    } else if (it == "List") {
                        badgeViewModel.buttonSelected("List")
                    } else if (it== "Profile") {
                        badgeViewModel.buttonSelected("Profile")
                    }
                }
            }
        }
        binding.dataSpinner.onItemSelectedListener = this
        return binding.root
    }

    // Called when the item is clicked
    fun onHomeClicked(count: Int) {
        // Set a new count
        badgeViewModel.countHome(count)
    }

    fun onListClicked(count: Int) {  badgeViewModel.countList(count) }

    fun onProfileClicked(count: Int) { badgeViewModel.countProfile(count) }


    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        val badgesSelected : String = parent.getItemAtPosition(pos).toString()
        badgeViewModel.spinnerSelected(badgesSelected)
        Log.d("HomeFragment", "Selected is : ${badgesSelected}")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}