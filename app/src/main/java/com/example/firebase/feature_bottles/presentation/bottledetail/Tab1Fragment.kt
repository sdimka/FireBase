package com.example.firebase.feature_bottles.presentation.bottledetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.databinding.FragmentTab1Binding
import com.example.firebase.BR
import com.example.firebase.R
import com.example.firebase.feature_bottles.presentation.BottleViewModel


class Tab1Fragment: Fragment() {

    private lateinit var viewModel: BottleViewModel

//    private var _binding: FragmentTab1Binding? = null
    private lateinit var binding: FragmentTab1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[BottleViewModel::class.java]

//        _binding = FragmentTab1Binding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab1, container, false)

//        binding.setVariable(BR.viewModel, viewModel)
        binding.viewModel = viewModel

        return binding.root
//        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.invalidateAll()

        binding.buttonSave.setOnClickListener {
            viewModel.saveBottle()
        }

        viewModel.getBottle().observe(viewLifecycleOwner) {
            binding.invalidateAll()
        }

    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
}