package com.example.firebase.feature_bottles.presentation.bottledetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.R
import com.example.firebase.feature_bottles.presentation.BottleViewModel
import kotlinx.android.synthetic.main.fragment_tab1.*

class Tab1Fragment: Fragment() {

    private lateinit var viewModel: BottleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(BottleViewModel::class.java)

        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text1.text = viewModel.javaClass.hashCode().toString()
    }
}