package com.example.firebase.feature_bottles.presentation.bottledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase.databinding.FragmentTab4Binding
import com.example.firebase.feature_bottles.presentation.BottleViewModel
import com.example.firebase.feature_bottles.presentation.bottledetail.foodcardrecycler.FoodCardListAdapter



class Tab4Fragment: Fragment() {

    private lateinit var viewModel: BottleViewModel

    private var _binding: FragmentTab4Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[BottleViewModel::class.java]
        _binding = FragmentTab4Binding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonSave.setOnClickListener {
            viewModel.saveBottle()
        }

        val fcAdapter = FoodCardListAdapter()
        fcAdapter.setClickListener {
            viewModel.onFoodCardSelect(it)
            fcAdapter.notifyDataSetChanged()
        }

        val lManager = GridLayoutManager(requireContext(), 8)

        binding.bottleEditorFoodCardRecycler.apply {
            adapter = fcAdapter
            layoutManager = lManager
        }

        viewModel.foodCardListData().observe(viewLifecycleOwner){
            fcAdapter.setList(it)
            fcAdapter.notifyDataSetChanged()
        }

        viewModel.getBottle().observe(viewLifecycleOwner){
            binding.invalidateAll()
            fcAdapter.setCurrentBottle(it)
            fcAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}