package com.example.firebase.feature_bottles.presentation.bottledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.BR
import com.example.firebase.R
import com.example.firebase.databinding.FragmentTab2Binding
import com.example.firebase.feature_bottles.presentation.BottleViewModel
//import kotlinx.android.synthetic.main.fragment_tab2.buttonSave

class Tab2Fragment: Fragment() {

    private lateinit var viewModel: BottleViewModel

    private lateinit var binding: FragmentTab2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[BottleViewModel::class.java]

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab2, container, false)
//        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_tab2)
        binding.setVariable(BR.viewModel, viewModel)
        binding.viewModel = viewModel

        return binding.root
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
}