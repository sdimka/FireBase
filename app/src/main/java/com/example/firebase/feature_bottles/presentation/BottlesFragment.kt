package com.example.firebase.feature_bottles.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.presentation.bottledetail.BottleItemEditorTabs
import com.example.firebase.feature_bottles.presentation.bottlelist.recyclerview.BottleViewAdapter
import kotlinx.android.synthetic.main.bottle_editor.*


class BottlesFragment: Fragment() {

    private val bottleList = arrayListOf<Bottle>()
    private val TAG = "BottlesFragment"

    private lateinit var viewModel: BottleViewModel

//    val bs = BottleFBService.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BottleViewModel::class.java)
        return inflater.inflate(R.layout.bottle_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val query = FirebaseDatabase.getInstance()
//            .reference
//            .child("Bottles")

//        val options: FirebaseRecyclerOptions<Bottle> =
//            FirebaseRecyclerOptions.Builder<Bottle>()
//                .setQuery(query, Bottle::class.java)
//                .setLifecycleOwner(this)
//                .build()

        val bAdapter = BottleViewAdapter()

//        requireActivity().supportFragmentManager
//            .beginTransaction()
//            .replace(
//                R.id.bottleEditorFrameContainer,
//                BottleItemEditor(),
//                "BottleItemEditor"
//            )
//            .commit()

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.bottleEditorFrameContainer,
                BottleItemEditorTabs(),
                "BottleTabsEditor"
            )
            .commit()

        bAdapter.setHolderClickListener { viewModel.selectBottle(it) }
        viewModel.getBottlesList().observe(viewLifecycleOwner) {
            bAdapter.setList(it)
        }

        recyclerBottle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bAdapter
        }

    }
}